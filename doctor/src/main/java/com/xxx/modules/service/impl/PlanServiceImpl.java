package com.xxx.modules.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.modules.entity.Order;
import com.xxx.modules.mapper.OrderMapper;
import com.xxx.modules.mapper.PlanMapper;
import com.xxx.modules.entity.Plan;
import com.xxx.modules.service.PlanService;
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
 * 排班
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@Service
public class PlanServiceImpl extends ServiceImpl<PlanMapper, Plan> implements PlanService {

    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private OrderMapper orderMapper;


    /**
     *  获取所有排班接口实现类
     * @param pageNum
     * @param pageSize
     * @param plan
     * @return
     */
    @Override
    public Result<?> selectPlanList(Plan plan, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Plan> data = planMapper.selectListInfo(plan);
        for (Plan plan1:data){
            QueryWrapper<Order> wrapper = new QueryWrapper<>();
            wrapper.eq("plan_id",plan1.getId());
            Integer count = orderMapper.selectCount(wrapper);
            plan1.setSubCount(plan1.getCount() - count);
        }
       return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }


    /**
     * 获取单个排班接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> selectPlanInfo(Integer id) {
        Plan plan = planMapper.selectById(id);
        return ResultUtil.success(1,"成功",plan);
    }

    /**
     * 保存排班接口实现类
     * @param plan
     * @return
     */
    @Override
    public Result<?> savePlanInfo(Plan plan) {
        QueryWrapper<Plan> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",plan.getUserId()).eq("plan_day",plan.getPlanDay())
                .eq("time",plan.getTime());
        Plan plan1 = planMapper.selectOne(wrapper);
        if (plan1 !=null){
            return ResultUtil.error(-1,"该医生该时间段已经安排过了");
        }
        if (plan.getTime() == 1 || plan.getTime() == 2){
            plan.setType(1);
        }else {
            plan.setType(2);
        }
        String weekdayManual = TimeUtil.getWeekdayManual(plan.getPlanDay());
        plan.setWeekDay(weekdayManual);
        plan.setCreateTime(TimeUtil.getCurrentTime());
        plan.setUpdateTime(TimeUtil.getCurrentTime());
        planMapper.insert(plan);
        return ResultUtil.success(1,"成功",null);
}

    /**
     * 更新排班接口实现类
     * @param plan
     * @return
     */
    @Override
    public Result<?> updatePlanInfo(Plan plan) {
        if (plan.getTime() == 1 || plan.getTime() == 2){
            plan.setType(1);
        }else {
            plan.setType(2);
        }
        String weekdayManual = TimeUtil.getWeekdayManual(plan.getPlanDay());
        plan.setWeekDay(weekdayManual);
        plan.setUpdateTime(TimeUtil.getCurrentTime());
        planMapper.updateById(plan);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除排班删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> delPlanInfo(Integer id) {
        planMapper.deleteById(id);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除排班接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result<?> delBatchPlanInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        planMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 导出
     * @param plan
     * @return
     */
    @Override
    public List<Plan> selectExcel(Plan plan) {
        return planMapper.selectListInfo(plan);
    }




}