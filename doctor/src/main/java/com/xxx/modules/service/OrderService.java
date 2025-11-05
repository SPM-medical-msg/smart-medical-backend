package com.xxx.modules.service;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.utils.Result;
import com.xxx.modules.entity.Order;
import java.util.List;
/**
 * 挂号
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
public interface OrderService extends IService<Order>{

    /**
     *  获取所有挂号接口
     * @param pageNum
     * @param pageSize
     * @param order
     * @return
     */
    Result <?>selectOrderList(Order order, Integer pageNum, Integer pageSize);


    /**
     *  获取单个挂号接口
     * @param id
     * @return
     */
    Result<?> selectOrderInfo(Integer id);

    /**
     * 保存挂号接口
     * @param order
     * @return
     */
    Result<?> saveOrderInfo(Order order);

    /**
     * 更新挂号接口
     * @param order
     * @return
     */
    Result<?> updateOrderInfo(Order order);

    /**
     * 根据id删除挂号接口
     * @param id
     * @return
     */
    Result<?> delOrderInfo(Integer id);

    /**
     * 根据id集合批量删除挂号接口
     * @param idList
     * @return
     */
    Result<?> delBatchOrderInfo(String idList);

    Result<?> selectOrderStaData(Integer type);

    Result<?> payOrderInfo(Order order);


        /**
     * 导出
     * @param order
     * @return
     */
    List<Order> selectExcel(Order order);
    
    

}