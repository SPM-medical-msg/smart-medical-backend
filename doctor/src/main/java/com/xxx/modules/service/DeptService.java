package com.xxx.modules.service;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.utils.Result;
import com.xxx.modules.entity.Dept;
import java.util.List;
/**
 * 科室
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
public interface DeptService extends IService<Dept>{

    /**
     *  获取所有科室接口
     * @param pageNum
     * @param pageSize
     * @param dept
     * @return
     */
    Result <?>selectDeptList(Dept dept, Integer pageNum, Integer pageSize);


    /**
     *  获取单个科室接口
     * @param id
     * @return
     */
    Result<?> selectDeptInfo(Integer id);

    /**
     * 保存科室接口
     * @param dept
     * @return
     */
    Result<?> saveDeptInfo(Dept dept);

    /**
     * 更新科室接口
     * @param dept
     * @return
     */
    Result<?> updateDeptInfo(Dept dept);

    /**
     * 根据id删除科室接口
     * @param id
     * @return
     */
    Result<?> delDeptInfo(Integer id);

    /**
     * 根据id集合批量删除科室接口
     * @param idList
     * @return
     */
    Result<?> delBatchDeptInfo(String idList);




}