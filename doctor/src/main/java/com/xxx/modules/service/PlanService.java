package com.xxx.modules.service;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.utils.Result;
import com.xxx.modules.entity.Plan;
import java.util.List;
/**
 * 排班
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
public interface PlanService extends IService<Plan>{

    /**
     *  获取所有排班接口
     * @param pageNum
     * @param pageSize
     * @param plan
     * @return
     */
    Result <?>selectPlanList(Plan plan, Integer pageNum, Integer pageSize);


    /**
     *  获取单个排班接口
     * @param id
     * @return
     */
    Result<?> selectPlanInfo(Integer id);

    /**
     * 保存排班接口
     * @param plan
     * @return
     */
    Result<?> savePlanInfo(Plan plan);

    /**
     * 更新排班接口
     * @param plan
     * @return
     */
    Result<?> updatePlanInfo(Plan plan);

    /**
     * 根据id删除排班接口
     * @param id
     * @return
     */
    Result<?> delPlanInfo(Integer id);

    /**
     * 根据id集合批量删除排班接口
     * @param idList
     * @return
     */
    Result<?> delBatchPlanInfo(String idList);



        /**
     * 导出
     * @param plan
     * @return
     */
    List<Plan> selectExcel(Plan plan);
    
    

}