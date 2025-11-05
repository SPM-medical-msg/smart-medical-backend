package com.xxx.modules.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.modules.entity.Order;
import com.xxx.modules.mapper.EvaluateMapper;
import com.xxx.modules.entity.Evaluate;
import com.xxx.modules.mapper.OrderMapper;
import com.xxx.modules.service.EvaluateService;
import com.xxx.modules.entity.User;
import com.xxx.modules.mapper.UserMapper;
import com.xxx.modules.utils.Result;
import com.xxx.modules.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import com.xxx.modules.utils.TimeUtil;

/**
 * 评论
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-04-05
 */
@Service
public class EvaluateServiceImpl extends ServiceImpl<EvaluateMapper, Evaluate> implements EvaluateService {

    @Autowired
    private EvaluateMapper evaluateMapper;



    /**
     *  获取所有评论接口实现类
     * @param pageNum
     * @param pageSize
     * @param evaluate
     * @return
     */
    @Override
    public Result<?> selectEvaluateList(Evaluate evaluate, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Evaluate> data = evaluateMapper.selectListInfo(evaluate);
       return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }


    /**
     * 获取单个评论接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> selectEvaluateInfo(Integer id) {
        Evaluate evaluate = evaluateMapper.selectById(id);
        return ResultUtil.success(1,"成功",evaluate);
    }

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 保存评论接口实现类
     * @param evaluate
     * @return
     */
    @Override
    public Result<?> saveEvaluateInfo(Evaluate evaluate) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",evaluate.getUserId()).eq("doctor_user_id",evaluate.getDoctorUserId()).eq("status",2);
        Integer count = orderMapper.selectCount(wrapper);
        if (count == null || count == 0){
            return ResultUtil.error(-1,"你还未挂号过该医生");
        }
        evaluate.setCreateTime(TimeUtil.getCurrentTime());
        evaluate.setUpdateTime(TimeUtil.getCurrentTime());
        evaluateMapper.insert(evaluate);
        return ResultUtil.success(1,"成功",null);
}

    /**
     * 更新评论接口实现类
     * @param evaluate
     * @return
     */
    @Override
    public Result<?> updateEvaluateInfo(Evaluate evaluate) {
        evaluate.setUpdateTime(TimeUtil.getCurrentTime());
        evaluateMapper.updateById(evaluate);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除评论删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> delEvaluateInfo(Integer id) {
        evaluateMapper.deleteById(id);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除评论接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result<?> delBatchEvaluateInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        evaluateMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }

}