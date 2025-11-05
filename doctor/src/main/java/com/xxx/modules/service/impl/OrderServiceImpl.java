package com.xxx.modules.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.modules.entity.Plan;
import com.xxx.modules.mapper.OrderMapper;
import com.xxx.modules.entity.Order;
import com.xxx.modules.mapper.PlanMapper;
import com.xxx.modules.service.OrderService;
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
import org.springframework.transaction.annotation.Transactional;

/**
 * 挂号
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;



    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<?> selectOrderStaData(Integer type){
        return ResultUtil.success(1,"正常",null);
    }
    @Override
    @Transactional
    public Result<?> payOrderInfo(Order order){
        Order orderFromDb = orderMapper.selectById(order.getId());
        if (orderFromDb.getStatus() == 2){
            return ResultUtil.error(-1,"该订单已经支付");
        }
        User user = userMapper.selectById(orderFromDb.getUserId());
        Double v = user.getMoney();
        Double totalPrice = orderFromDb.getPrice();
        if (totalPrice > v){
            return ResultUtil.error(-1,"余额不足");
        }
        orderFromDb.setStatus(2);
        orderMapper.updateById(orderFromDb);
        user.setMoney(v - totalPrice);
        userMapper.updateById(user);

        return ResultUtil.success(1,"正常",null);
    }

    /**
     *  获取所有挂号接口实现类
     * @param pageNum
     * @param pageSize
     * @param order
     * @return
     */
    @Override
    public Result<?> selectOrderList(Order order, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Order> data = orderMapper.selectListInfo(order);
       return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }


    /**
     * 获取单个挂号接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> selectOrderInfo(Integer id) {
        Order order = orderMapper.selectById(id);
        return ResultUtil.success(1,"成功",order);
    }

    @Autowired
    private PlanMapper planMapper;

    /**
     * 保存挂号接口实现类
     * @param order
     * @return
     */
    @Override
    public Result<?> saveOrderInfo(Order order) {
        order.setOrderNumber(TimeUtil.dateRandom18());
        order.setStatus(1);
        QueryWrapper<Order> orderNumberWrapper = new QueryWrapper<>();
        orderNumberWrapper.eq("order_number",order.getOrderNumber());
        Order orderNumberInfo = orderMapper.selectOne(orderNumberWrapper);
        if (orderNumberInfo != null){
            return ResultUtil.error(-1,"编号重复");
        }
        Plan plan = planMapper.selectById(order.getPlanId());
        order.setTime(plan.getTime());
        order.setWeekDay(plan.getWeekDay());
        order.setAppointDay(plan.getPlanDay());
        order.setPrice(plan.getMoney());
        User user1 = userMapper.selectById(order.getDoctorUserId());
        order.setDeptId(user1.getDeptId());
        order.setCreateTime(TimeUtil.getCurrentTime());
        order.setUpdateTime(TimeUtil.getCurrentTime());
        orderMapper.insert(order);

        return ResultUtil.success(1,"成功",null);
}

    /**
     * 更新挂号接口实现类
     * @param order
     * @return
     */
    @Override
    public Result<?> updateOrderInfo(Order order) {
        QueryWrapper<Order> orderNumberWrapper = new QueryWrapper<>();
        orderNumberWrapper.eq("order_number",order.getOrderNumber()).ne("id",order.getId());
        Order orderNumberInfo = orderMapper.selectOne(orderNumberWrapper);
        if (orderNumberInfo != null){
            return ResultUtil.error(-1,"编号重复");
        }
        order.setUpdateTime(TimeUtil.getCurrentTime());
        orderMapper.updateById(order);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除挂号删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> delOrderInfo(Integer id) {
        orderMapper.deleteById(id);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除挂号接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result<?> delBatchOrderInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        orderMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 导出
     * @param order
     * @return
     */
    @Override
    public List<Order> selectExcel(Order order) {
        return orderMapper.selectListInfo(order);
    }




}