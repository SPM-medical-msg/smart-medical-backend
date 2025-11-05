package com.xxx.modules.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.modules.entity.Drug;
import com.xxx.modules.entity.Order;
import com.xxx.modules.mapper.DiagnoseMapper;
import com.xxx.modules.entity.Diagnose;
import com.xxx.modules.mapper.DrugMapper;
import com.xxx.modules.mapper.OrderMapper;
import com.xxx.modules.service.DiagnoseService;
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
 * 诊断
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@Service
public class DiagnoseServiceImpl extends ServiceImpl<DiagnoseMapper, Diagnose> implements DiagnoseService {

    @Autowired
    private DiagnoseMapper diagnoseMapper;



    /**
     *  获取所有诊断接口实现类
     * @param pageNum
     * @param pageSize
     * @param diagnose
     * @return
     */
    @Override
    public Result<?> selectDiagnoseList(Diagnose diagnose, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Diagnose> data = diagnoseMapper.selectListInfo(diagnose);
       return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }


    /**
     * 获取单个诊断接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> selectDiagnoseInfo(Integer id) {
        Diagnose diagnose = diagnoseMapper.selectById(id);
        return ResultUtil.success(1,"成功",diagnose);
    }

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DrugMapper drugMapper;

    /**
     * 保存诊断接口实现类
     * @param diagnose
     * @return
     */
    @Override
    @Transactional
    public Result<?> saveDiagnoseInfo(Diagnose diagnose) {
        Drug drug = drugMapper.selectById(diagnose.getDrugId());
        if (diagnose.getCount() > drug.getCount()){
            return ResultUtil.error(-1,"库存不足");
        }
        Order order = orderMapper.selectById(diagnose.getOrderId());
        diagnose.setUserId(order.getUserId());
        diagnose.setCreateTime(TimeUtil.getCurrentTime());
        diagnose.setUpdateTime(TimeUtil.getCurrentTime());
        diagnoseMapper.insert(diagnose);
        drug.setCount(drug.getCount() - diagnose.getCount());
        drugMapper.updateById(drug);
//        order.setStatus(3);
//        orderMapper.updateById(order);
        return ResultUtil.success(1,"成功",null);
}

    /**
     * 更新诊断接口实现类
     * @param diagnose
     * @return
     */
    @Override
    public Result<?> updateDiagnoseInfo(Diagnose diagnose) {
        diagnose.setUpdateTime(TimeUtil.getCurrentTime());
        diagnoseMapper.updateById(diagnose);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除诊断删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> delDiagnoseInfo(Integer id) {
        diagnoseMapper.deleteById(id);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除诊断接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result<?> delBatchDiagnoseInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        diagnoseMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }

}