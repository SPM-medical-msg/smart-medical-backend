package com.xxx.modules.service;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.utils.Result;
import com.xxx.modules.entity.Sort;
import java.util.List;
/**
 * 类型
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-12-19
 */
public interface SortService extends IService<Sort>{

    /**
     *  获取所有类型接口
     * @param pageNum
     * @param pageSize
     * @param sort
     * @return
     */
    Result <?>selectSortList(Sort sort, Integer pageNum, Integer pageSize);


    /**
     *  获取单个类型接口
     * @param id
     * @return
     */
    Result<?> selectSortInfo(Integer id);

    /**
     * 保存类型接口
     * @param sort
     * @return
     */
    Result<?> saveSortInfo(Sort sort);

    /**
     * 更新类型接口
     * @param sort
     * @return
     */
    Result<?> updateSortInfo(Sort sort);

    /**
     * 根据id删除类型接口
     * @param id
     * @return
     */
    Result<?> delSortInfo(Integer id);

    /**
     * 根据id集合批量删除类型接口
     * @param idList
     * @return
     */
    Result<?> delBatchSortInfo(String idList);




}