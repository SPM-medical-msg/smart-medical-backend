package com.xxx.modules.service;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.utils.Result;
import com.xxx.modules.entity.Drug;
import java.util.List;
/**
 * 药品
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
public interface DrugService extends IService<Drug>{

    /**
     *  获取所有药品接口
     * @param pageNum
     * @param pageSize
     * @param drug
     * @return
     */
    Result <?>selectDrugList(Drug drug, Integer pageNum, Integer pageSize);


    /**
     *  获取单个药品接口
     * @param id
     * @return
     */
    Result<?> selectDrugInfo(Integer id);

    /**
     * 保存药品接口
     * @param drug
     * @return
     */
    Result<?> saveDrugInfo(Drug drug);

    /**
     * 更新药品接口
     * @param drug
     * @return
     */
    Result<?> updateDrugInfo(Drug drug);

    /**
     * 根据id删除药品接口
     * @param id
     * @return
     */
    Result<?> delDrugInfo(Integer id);

    /**
     * 根据id集合批量删除药品接口
     * @param idList
     * @return
     */
    Result<?> delBatchDrugInfo(String idList);


}