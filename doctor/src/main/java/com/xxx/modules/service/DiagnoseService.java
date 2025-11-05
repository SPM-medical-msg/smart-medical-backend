package com.xxx.modules.service;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.utils.Result;
import com.xxx.modules.entity.Diagnose;
import java.util.List;
/**
 * 诊断
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
public interface DiagnoseService extends IService<Diagnose>{

    /**
     *  获取所有诊断接口
     * @param pageNum
     * @param pageSize
     * @param diagnose
     * @return
     */
    Result <?>selectDiagnoseList(Diagnose diagnose, Integer pageNum, Integer pageSize);


    /**
     *  获取单个诊断接口
     * @param id
     * @return
     */
    Result<?> selectDiagnoseInfo(Integer id);

    /**
     * 保存诊断接口
     * @param diagnose
     * @return
     */
    Result<?> saveDiagnoseInfo(Diagnose diagnose);

    /**
     * 更新诊断接口
     * @param diagnose
     * @return
     */
    Result<?> updateDiagnoseInfo(Diagnose diagnose);

    /**
     * 根据id删除诊断接口
     * @param id
     * @return
     */
    Result<?> delDiagnoseInfo(Integer id);

    /**
     * 根据id集合批量删除诊断接口
     * @param idList
     * @return
     */
    Result<?> delBatchDiagnoseInfo(String idList);




}