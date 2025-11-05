package com.xxx.modules.service;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.utils.Result;
import com.xxx.modules.entity.Exchange;
import java.util.List;
/**
 * 药品订单
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
public interface ExchangeService extends IService<Exchange>{

    /**
     *  获取所有药品订单接口
     * @param pageNum
     * @param pageSize
     * @param exchange
     * @return
     */
    Result <?>selectExchangeList(Exchange exchange, Integer pageNum, Integer pageSize);


    /**
     *  获取单个药品订单接口
     * @param id
     * @return
     */
    Result<?> selectExchangeInfo(Integer id);

    /**
     * 保存药品订单接口
     * @param exchange
     * @return
     */
    Result<?> saveExchangeInfo(Exchange exchange);

    /**
     * 更新药品订单接口
     * @param exchange
     * @return
     */
    Result<?> updateExchangeInfo(Exchange exchange);

    /**
     * 根据id删除药品订单接口
     * @param id
     * @return
     */
    Result<?> delExchangeInfo(Integer id);

    /**
     * 根据id集合批量删除药品订单接口
     * @param idList
     * @return
     */
    Result<?> delBatchExchangeInfo(String idList);


    Result<?> payExchangeInfo(Exchange exchange);
}