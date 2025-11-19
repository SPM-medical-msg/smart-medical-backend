package com.xxx.modules.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.modules.entity.Drug;
import com.xxx.modules.mapper.DrugMapper;
import com.xxx.modules.mapper.PurchaseMapper;
import com.xxx.modules.entity.Purchase;
import com.xxx.modules.service.PurchaseService;
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
import org.springframework.transaction.annotation.Transactional;

/**
 * 采购
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase> implements PurchaseService {

    @Autowired
    private PurchaseMapper purchaseMapper;



    /**
     *  获取所有采购接口实现类
     * @param pageNum
     * @param pageSize
     * @param purchase
     * @return
     */
    @Override
    public Result<?> selectPurchaseList(Purchase purchase, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Purchase> data = purchaseMapper.selectListInfo(purchase);
       return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }


    /**
     * 获取单个采购接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> selectPurchaseInfo(Integer id) {
        Purchase purchase = purchaseMapper.selectById(id);
        return ResultUtil.success(1,"成功",purchase);
    }

    /**
     * 保存采购接口实现类
     * @param purchase
     * @return
     */
    @Override
    public Result<?> savePurchaseInfo(Purchase purchase) {
        purchase.setOrderNumber(TimeUtil.dateRandom18());
        purchase.setStatus(1);
        purchase.setCreateTime(TimeUtil.getCurrentTime());
        purchase.setUpdateTime(TimeUtil.getCurrentTime());
        purchaseMapper.insert(purchase);
        return ResultUtil.success(1,"成功",null);
}

@Autowired
private DrugMapper drugMapper;

    /**
     * 更新采购接口实现类
     * @param purchase
     * @return
     */
    @Override
    @Transactional
    public Result<?> updatePurchaseInfo(Purchase purchase) {
        Purchase purchase1 = purchaseMapper.selectById(purchase.getId());
        if (purchase1.getStatus() == 2){
            return ResultUtil.error(-1,"该申请已经审核通过了");
        }
        purchase.setUpdateTime(TimeUtil.getCurrentTime());
        purchaseMapper.updateById(purchase);
        if (purchase.getStatus() == 2){
            Drug drug = drugMapper.selectById(purchase1.getDrugId());
            drug.setCount(drug.getCount() + purchase1.getCount());
            drugMapper.updateById(drug);
        }
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除采购删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> delPurchaseInfo(Integer id) {
        purchaseMapper.deleteById(id);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除采购接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result<?> delBatchPurchaseInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        purchaseMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }

}