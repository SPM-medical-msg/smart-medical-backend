package com.xxx.modules.service.impl;
import com.baomidou.mybatisplus.core.conditions.interfaces.Func;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.modules.entity.Friend;
import com.alibaba.fastjson.JSONObject;
import com.xxx.modules.mapper.*;
import com.xxx.modules.entity.Order;
import com.xxx.modules.service.OrderService;
import com.xxx.modules.entity.User;
import com.xxx.modules.utils.Result;
import com.xxx.modules.utils.ResultUtil;
import com.xxx.modules.mqtt.MqttGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

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
    private FriendMapper friendMapper;
    @Autowired
    private FriendMessageMapper friendMessageMapper;
    @Autowired
    private MqttGateway mqttGateway;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<?> selectOrderStaData(Integer type){
        return ResultUtil.success(1,"正常",null);
    }
    @Override
    @Transactional
    public Result<?> payOrderInfo(Order order) {
        Order orderFromDb = orderMapper.selectById(order.getId());
        if (orderFromDb.getStatus() == 2) {
            return ResultUtil.error(-1, "该订单已经支付");
        }

        User user = userMapper.selectById(orderFromDb.getUserId());
        Double v = user.getMoney();
        Double totalPrice = orderFromDb.getPrice();
        if (totalPrice > v) {
            return ResultUtil.error(-1, "余额不足");
        }

        // 1. 更新订单状态
        orderFromDb.setStatus(2);
        orderMapper.updateById(orderFromDb);

        // 2. 扣除用户余额
        user.setMoney(v - totalPrice);
        userMapper.updateById(user);

        // 3. 自动建立医患好友关系
        Integer patientId = orderFromDb.getUserId();    // 患者ID
        Integer doctorId = orderFromDb.getDoctorUserId();   // 医生ID（根据你的字段名调整）

        // 检查是否已经是好友
        if (!checkIsFriend(patientId, doctorId)) {
            // 不是好友，自动建立双向好友关系
            createFriendRelation(patientId, doctorId);

            // 发送MQTT通知给医生：有新患者
            User patient = userMapper.selectById(patientId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", "您有新的患者咨询，患者：" + patient.getRealName());
            jsonObject.put("type", "NEW_PATIENT");
            jsonObject.put("patientId", patientId);
            jsonObject.put("orderId", orderFromDb.getId());
            mqttGateway.sendToMqtt("ADD/APPLY/" + doctorId, jsonObject.toString());
        }

        // 发送MQTT通知给患者：支付成功，可以开始咨询
//        JSONObject patientNotify = new JSONObject();
//        patientNotify.put("msg", "支付成功，咨询通道已建立");
//        patientNotify.put("type", "PAY_SUCCESS");
//        patientNotify.put("doctorId", doctorId);
//        patientNotify.put("orderId", orderFromDb.getId());
//        mqttGateway.sendToMqtt("PAY/SUCCESS/" + patientId, patientNotify.toString());

        return ResultUtil.success(1, "支付成功", null);
    }

    /**
     * 检查是否已经是好友关系
     */
    private boolean checkIsFriend(Integer userId1, Integer userId2) {
        LambdaQueryWrapper<Friend> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Friend::getUserId, userId1)
                .eq(Friend::getFriendId, userId2)
                .eq(Friend::getStatus, 2);  // status=2 表示已是好友
        Friend friend = friendMapper.selectOne(wrapper);
        return friend != null;
    }

    /**
     * 建立双向好友关系（直接是好友状态，无需申请）
     */
    private void createFriendRelation(Integer patientId, Integer doctorId) {
        String currentTime = TimeUtil.getCurrentTime();

        // 患者 -> 医生 的好友记录
        LambdaQueryWrapper<Friend> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(Friend::getUserId, patientId).eq(Friend::getFriendId, doctorId);
        Friend friend1 = friendMapper.selectOne(wrapper1);

        if (friend1 == null) {
            Friend newFriend1 = new Friend();
            newFriend1.setUserId(patientId);
            newFriend1.setFriendId(doctorId);
            newFriend1.setStatus(2);  // 直接设为好友状态
            newFriend1.setFriendType(2);  // 2表示医患关系（可选，用于区分）
            newFriend1.setActiveAddUserId(patientId);
            newFriend1.setAcceptAddUserId(doctorId);
            newFriend1.setApplyMessage("于"+currentTime+"提交申请");
            newFriend1.setCreateTime(currentTime);
            newFriend1.setUpdateTime(currentTime);
            friendMapper.insert(newFriend1);
        } else {
            friend1.setStatus(2);
            friend1.setUpdateTime(currentTime);
            friendMapper.updateById(friend1);
        }

        // 医生 -> 患者 的好友记录
        LambdaQueryWrapper<Friend> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(Friend::getUserId, doctorId).eq(Friend::getFriendId, patientId);
        Friend friend2 = friendMapper.selectOne(wrapper2);

        if (friend2 == null) {
            Friend newFriend2 = new Friend();
            newFriend2.setUserId(doctorId);
            newFriend2.setFriendId(patientId);
            newFriend2.setStatus(2);  // 直接设为好友状态
            newFriend2.setFriendType(2);  // 2表示医患关系
            newFriend2.setActiveAddUserId(patientId);
            newFriend2.setAcceptAddUserId(doctorId);
            newFriend2.setApplyMessage("于"+currentTime+"提交申请");
            newFriend2.setCreateTime(currentTime);
            newFriend2.setUpdateTime(currentTime);
            friendMapper.insert(newFriend2);
        } else {
            friend2.setStatus(2);
            friend2.setUpdateTime(currentTime);
            friendMapper.updateById(friend2);
        }
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
        PageHelper.startPage(pageNum,pageSize, "create_time desc");
        // 添加排序：按创建时间降序
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

        // 检查订单号重复
        QueryWrapper<Order> orderNumberWrapper = new QueryWrapper<>();
        orderNumberWrapper.eq("order_number", order.getOrderNumber());
        if (orderMapper.selectOne(orderNumberWrapper) != null) {
            return ResultUtil.error(-1, "编号重复");
        }

        // 实时咨询部分
        order.setTime(getTimeSlot(TimeUtil.getCurrentHour()));
        order.setWeekDay(TimeUtil.getCurrentWeekDayInChinese());
        order.setAppointDay(TimeUtil.getCurrentDay());

        // 获取医生信息并设置价格
        User doctor = userMapper.selectById(order.getDoctorUserId());
        if (doctor == null) {
            return ResultUtil.error(-1, "医生信息不存在");
        }
        order.setPrice(0.0);
        order.setDeptId(doctor.getDeptId());

        // 设置时间戳
        String currentTime = TimeUtil.getCurrentTime();
        order.setCreateTime(currentTime);
        order.setUpdateTime(currentTime);

        // 插入订单
        int result = orderMapper.insert(order);

        if (result > 0) {
            // 使用Map构建返回数据
            Map<String, Object> resultData = new HashMap<>();
            resultData.put("id", order.getId());
            resultData.put("orderNumber", order.getOrderNumber());
            resultData.put("price", order.getPrice());
            resultData.put("time", order.getTime());
            resultData.put("createTime", order.getCreateTime());

            return ResultUtil.success(1, "订单创建成功", resultData);
        } else {
            return ResultUtil.error(-1, "订单创建失败");
        }
    }
    /**
     * 辅助方法：根据小时获取时间段
     * @param hour 当前小时（0-23）
     * @return 时间段编号（1-4）
     */
    private int getTimeSlot(int hour) {
        if (hour >= 8 && hour < 10) {
            return 1;  // 8-10点
        } else if (hour >= 10 && hour < 12) {
            return 2;  // 10-12点
        } else if (hour >= 14 && hour < 16) {
            return 3;  // 14-16点
        } else if (hour >= 16 && hour < 18) {
            return 4;  // 16-18点
        } else {
            // 非工作时间的处理
            if (hour < 8) {
                return 1;  // 早上8点前，返回第一个时间段
            } else if (hour >= 12 && hour < 14) {
                return 3;  // 午休时间，返回下午第一个时间段
            } else {
                return 4;  // 晚上，返回最后一个时间段
            }
        }
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
     * 根据订单ID更新订单状态
     * @param orderId 订单ID
     * @param status 订单状态（1-待支付 2-等待医生联系 3-已完成 4-已退款/已取消）
     * @return
     */

    /**
     * 根据订单ID更新订单状态接口实现类
     * @param orderId 订单ID
     * @param status 订单状态（1-等待医生联系 2-已完成 3-已取消 4-已退款）
     * @return
     */
    @Override
    public Result<?> updateOrderStatus(Integer orderId, Integer status) {
        // 1. 参数校验
        if (orderId == null || orderId <= 0) {
            return ResultUtil.error(-1, "订单ID无效");
        }

        if (status == null) {
            return ResultUtil.error(-1, "订单状态不能为空");
        }

        // 2. 校验状态值范围
        if (status < 1 || status > 4) {
            return ResultUtil.error(-1, "订单状态值无效");
        }

        // 3. 查询订单是否存在
        Order existOrder = orderMapper.selectById(orderId);
        if (existOrder == null) {
            return ResultUtil.error(-1, "订单不存在");
        }

        // 4. 检查订单状态是否已经是目标状态
        if (existOrder.getStatus() != null && existOrder.getStatus().equals(status)) {
            return ResultUtil.error(-1, "订单已经是该状态，无需更新");
        }

        // 5. 业务逻辑校验（可选）
        // 例如：已完成的订单不能改回待支付状态
        if (existOrder.getStatus() != null) {
            // 已完成(2)的订单不能改为等待联系(1)
            if (existOrder.getStatus() == 2 && status == 1) {
                return ResultUtil.error(-1, "不可重复支付订单");
            }
            // 已取消(3)或已退款(4)的订单不能改为其他状态
            if ((existOrder.getStatus() == 3 || existOrder.getStatus() == 4) && status < 3) {
                return ResultUtil.error(-1, "已取消或已完成的订单不能恢复");
            }
        }

        // 6. 创建更新对象
        Order updateOrder = new Order();
        updateOrder.setId(orderId);
        updateOrder.setStatus(status);
        updateOrder.setUpdateTime(TimeUtil.getCurrentTime());

        // 7. 执行更新
        try {
            int result = orderMapper.updateById(updateOrder);
            if (result > 0) {
                return ResultUtil.success(1, "订单状态更新成功", null);
            } else {
                return ResultUtil.error(-1, "订单状态更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(-1, "系统异常，更新失败");
        }
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