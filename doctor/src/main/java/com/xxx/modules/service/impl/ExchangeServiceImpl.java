package com.xxx.modules.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.modules.entity.Drug;
import com.xxx.modules.mapper.DrugMapper;
import com.xxx.modules.mapper.ExchangeMapper;
import com.xxx.modules.entity.Exchange;
import com.xxx.modules.service.ExchangeService;
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
 * 药品订单
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
@Service
public class ExchangeServiceImpl extends ServiceImpl<ExchangeMapper, Exchange> implements ExchangeService {

    @Autowired
    private ExchangeMapper exchangeMapper;



    /**
     *  获取所有药品订单接口实现类
     * @param pageNum
     * @param pageSize
     * @param exchange
     * @return
     */
    @Override
    public Result<?> selectExchangeList(Exchange exchange, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Exchange> data = exchangeMapper.selectListInfo(exchange);
        return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }


    /**
     * 获取单个药品订单接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> selectExchangeInfo(Integer id) {
        Exchange exchange = exchangeMapper.selectById(id);
        return ResultUtil.success(1,"成功",exchange);
    }

    @Autowired
    private DrugMapper drugMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 保存药品订单接口实现类
     * @param exchange
     * @return
     */
    @Override
    public Result<?> saveExchangeInfo(Exchange exchange) {
        Drug drug = drugMapper.selectById(exchange.getDrugId());
        Integer count = exchange.getCount();
        Integer count1 = drug.getCount();
        if (count > count1){
            return ResultUtil.error(-1,"库存不足");
        }
        User user = userMapper.selectById(exchange.getUserId());
        exchange.setPrice(drug.getPrice());
        exchange.setTotalPrice(drug.getPrice() * exchange.getCount());
        exchange.setCount(exchange.getCount());
        exchange.setOrderNumber(TimeUtil.dateRandom18());
        exchange.setAddress(user.getAddress());
        exchange.setPhone(user.getPhone());
        exchange.setOrderNumber(TimeUtil.dateRandom18());
        exchange.setStatus(1);
        exchange.setCreateTime(TimeUtil.getCurrentTime());
        exchange.setUpdateTime(TimeUtil.getCurrentTime());
        exchangeMapper.insert(exchange);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 更新药品订单接口实现类
     * @param exchange
     * @return
     */
    @Override
    public Result<?> updateExchangeInfo(Exchange exchange) {
        exchange.setUpdateTime(TimeUtil.getCurrentTime());
        exchangeMapper.updateById(exchange);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除药品订单删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> delExchangeInfo(Integer id) {
        exchangeMapper.deleteById(id);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除药品订单接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result<?> delBatchExchangeInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        exchangeMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }

    @Override
    @Transactional
    public Result<?> payExchangeInfo(Exchange exchange) {
        Exchange exchange1 = exchangeMapper.selectById(exchange.getId());
        if (exchange1.getStatus() !=1){
            return ResultUtil.error(-1,"您已经支付过了");
        }
        Drug drug = drugMapper.selectById(exchange1.getDrugId());
        if (exchange1.getCount() > drug.getCount()){
            return ResultUtil.error(-1,"库存不足");
        }
        User user = userMapper.selectById(exchange1.getUserId());
        if (exchange1.getTotalPrice() > user.getMoney()){
            return ResultUtil.error(-1,"余额不足");
        }
        exchange1.setStatus(2);
        exchangeMapper.updateById(exchange1);
        drug.setCount(drug.getCount() - exchange1.getCount());
        drugMapper.updateById(drug);

        user.setMoney(user.getMoney() - exchange1.getTotalPrice());
        userMapper.updateById(user);

        exchangeMapper.updateById(exchange1);

        return ResultUtil.success(1,"正常",null);
    }

}