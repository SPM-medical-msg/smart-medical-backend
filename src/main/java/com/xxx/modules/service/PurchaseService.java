package com.xxx.modules.service;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.utils.Result;
import com.xxx.modules.entity.Purchase;
import java.util.List;
/**
 * 采购
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
public interface PurchaseService extends IService<Purchase>{

    /**
     *  获取所有采购接口
     * @param pageNum
     * @param pageSize
     * @param purchase
     * @return
     */
    Result <?>selectPurchaseList(Purchase purchase, Integer pageNum, Integer pageSize);


    /**
     *  获取单个采购接口
     * @param id
     * @return
     */
    Result<?> selectPurchaseInfo(Integer id);

    /**
     * 保存采购接口
     * @param purchase
     * @return
     */
    Result<?> savePurchaseInfo(Purchase purchase);

    /**
     * 更新采购接口
     * @param purchase
     * @return
     */
    Result<?> updatePurchaseInfo(Purchase purchase);

    /**
     * 根据id删除采购接口
     * @param id
     * @return
     */
    Result<?> delPurchaseInfo(Integer id);

    /**
     * 根据id集合批量删除采购接口
     * @param idList
     * @return
     */
    Result<?> delBatchPurchaseInfo(String idList);




}