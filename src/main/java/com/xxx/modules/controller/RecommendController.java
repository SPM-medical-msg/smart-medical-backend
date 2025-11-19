package com.xxx.modules.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.modules.config.UserCF;
import com.xxx.modules.entity.Order;
import com.xxx.modules.entity.User;
import com.xxx.modules.mapper.OrderMapper;
import com.xxx.modules.mapper.UserMapper;
import com.xxx.modules.utils.Result;
import com.xxx.modules.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common/user")
@Slf4j
public class RecommendController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;


    @GetMapping("/getRecommendList")
    public Result getRecommendList(Integer userId){

        if (userId == null){
            return ResultUtil.error(-1,"用户ID不得为空");
        }
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        Integer count = orderMapper.selectCount(wrapper);
        if (count == null || count == 0){
            log.info("用户暂无评分信息,走随机推荐...");
            List<User> userList = userMapper.selectList(null);
            List<User> list = createRandoms(userList, 3);
            return ResultUtil.success(1,"正常",list);
        }
        log.info("用户有评分信息走协同过滤推荐...");
        //查询出已经收藏过的用户
        List<Integer> list = orderMapper.selectDistinctUserId();
        //查询用户对物品的评分,我们是收藏的,所以所有的评分默认就是1了;
        Map<Integer, Map<Integer, Double>> data = new HashMap<>();
        for (Integer id:list){
            QueryWrapper<Order> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("user_id",id);
            List<Order> favoriteList = orderMapper.selectList(wrapper1);
            Map<Integer,Double> map = new HashMap<>();
            for (Order order:favoriteList){
                map.put(order.getDoctorUserId(),1.0);
            }
            data.put(id,map);
        }

        UserCF userCF = new UserCF(data);
        List<Integer> recommendIdList = userCF.recommend(userId, 2, 3);

        if (recommendIdList !=null && recommendIdList.size() > 0){
            QueryWrapper<User> wrapper1 = new QueryWrapper<>();
            wrapper1.in("id",recommendIdList);
            List<User> userList = userMapper.selectList(wrapper1);
            return ResultUtil.success(1,"正常",userList);
        }else {
            return ResultUtil.success(1,"正常",new ArrayList<>());
        }

    }


    /**
     * 从集合中随机取出N个不重复的元素
     * @param list 需要被取出数据的集合
     * @param n 取出的元素数量
     * @return
     */
    private List<User> createRandoms(List<User> list, int n) {
        Map<Integer,String> map = new HashMap();
        List<User> news = new ArrayList();
        if (list.size() <= n) {
            return list;
        } else {
            while (map.size() < n) {
                int random = (int)(Math.random() * list.size());
                if (!map.containsKey(random)) {
                    map.put(random, "");
                    news.add(list.get(random));
                }
            }
            return news;
        }
    }
}
