package com.xxx.modules.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.modules.mapper.DrugMapper;
import com.xxx.modules.entity.Drug;
import com.xxx.modules.service.DrugService;
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

/**
 * 药品
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
@Service
public class DrugServiceImpl extends ServiceImpl<DrugMapper, Drug> implements DrugService {

    @Autowired
    private DrugMapper drugMapper;



    /**
     *  获取所有药品接口实现类
     * @param pageNum
     * @param pageSize
     * @param drug
     * @return
     */
    @Override
    public Result<?> selectDrugList(Drug drug, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Drug> data = drugMapper.selectListInfo(drug);
       return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }


    /**
     * 获取单个药品接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> selectDrugInfo(Integer id) {
        Drug drug = drugMapper.selectById(id);
        return ResultUtil.success(1,"成功",drug);
    }

    /**
     * 保存药品接口实现类
     * @param drug
     * @return
     */
    @Override
    public Result<?> saveDrugInfo(Drug drug) {
        drug.setCreateTime(TimeUtil.getCurrentTime());
        drug.setUpdateTime(TimeUtil.getCurrentTime());
        drugMapper.insert(drug);
        return ResultUtil.success(1,"成功",null);
}

    /**
     * 更新药品接口实现类
     * @param drug
     * @return
     */
    @Override
    public Result<?> updateDrugInfo(Drug drug) {
        drug.setUpdateTime(TimeUtil.getCurrentTime());
        drugMapper.updateById(drug);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除药品删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> delDrugInfo(Integer id) {
        drugMapper.deleteById(id);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除药品接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result<?> delBatchDrugInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        drugMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }

}