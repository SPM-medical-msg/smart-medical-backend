package com.xxx.modules.service;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.utils.Result;
import com.xxx.modules.entity.Evaluate;
import java.util.List;
/**
 * 评论
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-04-05
 */
public interface EvaluateService extends IService<Evaluate>{

    /**
     *  获取所有评论接口
     * @param pageNum
     * @param pageSize
     * @param evaluate
     * @return
     */
    Result <?>selectEvaluateList(Evaluate evaluate, Integer pageNum, Integer pageSize);


    /**
     *  获取单个评论接口
     * @param id
     * @return
     */
    Result<?> selectEvaluateInfo(Integer id);

    /**
     * 保存评论接口
     * @param evaluate
     * @return
     */
    Result<?> saveEvaluateInfo(Evaluate evaluate);

    /**
     * 更新评论接口
     * @param evaluate
     * @return
     */
    Result<?> updateEvaluateInfo(Evaluate evaluate);

    /**
     * 根据id删除评论接口
     * @param id
     * @return
     */
    Result<?> delEvaluateInfo(Integer id);

    /**
     * 根据id集合批量删除评论接口
     * @param idList
     * @return
     */
    Result<?> delBatchEvaluateInfo(String idList);




}