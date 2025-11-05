package com.xxx.modules.service;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.utils.Result;
import com.xxx.modules.entity.Alarm;
import java.util.List;
/**
 * 报警
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
public interface AlarmService extends IService<Alarm>{

    /**
     *  获取所有报警接口
     * @param pageNum
     * @param pageSize
     * @param alarm
     * @return
     */
    Result <?>selectAlarmList(Alarm alarm, Integer pageNum, Integer pageSize);


    /**
     *  获取单个报警接口
     * @param id
     * @return
     */
    Result<?> selectAlarmInfo(Integer id);

    /**
     * 保存报警接口
     * @param alarm
     * @return
     */
    Result<?> saveAlarmInfo(Alarm alarm);

    /**
     * 更新报警接口
     * @param alarm
     * @return
     */
    Result<?> updateAlarmInfo(Alarm alarm);

    /**
     * 根据id删除报警接口
     * @param id
     * @return
     */
    Result<?> delAlarmInfo(Integer id);

    /**
     * 根据id集合批量删除报警接口
     * @param idList
     * @return
     */
    Result<?> delBatchAlarmInfo(String idList);




}