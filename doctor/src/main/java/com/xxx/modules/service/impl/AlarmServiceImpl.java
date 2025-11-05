package com.xxx.modules.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.modules.mapper.AlarmMapper;
import com.xxx.modules.entity.Alarm;
import com.xxx.modules.service.AlarmService;
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
 * 报警
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
@Service
public class AlarmServiceImpl extends ServiceImpl<AlarmMapper, Alarm> implements AlarmService {

    @Autowired
    private AlarmMapper alarmMapper;



    /**
     *  获取所有报警接口实现类
     * @param pageNum
     * @param pageSize
     * @param alarm
     * @return
     */
    @Override
    public Result<?> selectAlarmList(Alarm alarm, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Alarm> data = alarmMapper.selectListInfo(alarm);
       return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }


    /**
     * 获取单个报警接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> selectAlarmInfo(Integer id) {
        Alarm alarm = alarmMapper.selectById(id);
        return ResultUtil.success(1,"成功",alarm);
    }

    /**
     * 保存报警接口实现类
     * @param alarm
     * @return
     */
    @Override
    public Result<?> saveAlarmInfo(Alarm alarm) {
        alarm.setStatus(1);
        alarm.setCreateTime(TimeUtil.getCurrentTime());
        alarm.setUpdateTime(TimeUtil.getCurrentTime());
        alarmMapper.insert(alarm);
        return ResultUtil.success(1,"成功",null);
}

    /**
     * 更新报警接口实现类
     * @param alarm
     * @return
     */
    @Override
    public Result<?> updateAlarmInfo(Alarm alarm) {
        alarm.setUpdateTime(TimeUtil.getCurrentTime());
        alarmMapper.updateById(alarm);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除报警删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> delAlarmInfo(Integer id) {
        alarmMapper.deleteById(id);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除报警接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result<?> delBatchAlarmInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        alarmMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }

}