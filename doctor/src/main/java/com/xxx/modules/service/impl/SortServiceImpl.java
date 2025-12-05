package com.xxx.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.modules.mapper.SortMapper;
import com.xxx.modules.mapper.DrugMapper;
import com.xxx.modules.entity.Sort;
import com.xxx.modules.entity.Drug;  // ← 添加这行
import com.xxx.modules.service.SortService;
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
 * 类型
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-12-19
 */
@Service
public class SortServiceImpl extends ServiceImpl<SortMapper, Sort> implements SortService {  // ← 修复这行

    @Autowired
    private SortMapper sortMapper;

    @Autowired
    private DrugMapper drugMapper;

    /**
     * 获取所有类型接口实现类
     */
    @Override
    public Result<?> selectSortList(Sort sort, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<Sort> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        if (sort.getSortName() != null) {
            wrapper.like("sort_name", sort.getSortName());
        }
        List<Sort> data = sortMapper.selectList(wrapper);

        // 统计每个分类的药品数量
        for (Sort s : data) {
            QueryWrapper<Drug> drugWrapper = new QueryWrapper<>();
            drugWrapper.eq("sort_id", s.getId());
            Integer count = drugMapper.selectCount(drugWrapper);
            s.setDrugCount(count.intValue());
        }

        return ResultUtil.success(1, "成功", new PageInfo<>(data));
    }

    /**
     * 获取单个类型接口实现类
     */
    @Override
    public Result<?> selectSortInfo(Integer id) {
        Sort sort = sortMapper.selectById(id);
        return ResultUtil.success(1, "成功", sort);
    }

    /**
     * 保存类型接口实现类
     */
    @Override
    public Result<?> saveSortInfo(Sort sort) {
        QueryWrapper<Sort> sortNameWrapper = new QueryWrapper<>();
        sortNameWrapper.eq("sort_name", sort.getSortName());
        Sort sortNameInfo = sortMapper.selectOne(sortNameWrapper);
        if (sortNameInfo != null) {
            return ResultUtil.error(-1, "分类重复");
        }
        sort.setCreateTime(TimeUtil.getCurrentTime());
        sort.setUpdateTime(TimeUtil.getCurrentTime());
        sortMapper.insert(sort);
        return ResultUtil.success(1, "成功", null);
    }

    /**
     * 更新类型接口实现类
     */
    @Override
    public Result<?> updateSortInfo(Sort sort) {
        QueryWrapper<Sort> sortNameWrapper = new QueryWrapper<>();
        sortNameWrapper.eq("sort_name", sort.getSortName()).ne("id", sort.getId());
        Sort sortNameInfo = sortMapper.selectOne(sortNameWrapper);
        if (sortNameInfo != null) {
            return ResultUtil.error(-1, "分类重复");
        }
        sort.setUpdateTime(TimeUtil.getCurrentTime());
        sortMapper.updateById(sort);
        return ResultUtil.success(1, "成功", null);
    }

    /**
     * 根据id删除类型删除接口实现类
     */
    @Override
    public Result<?> delSortInfo(Integer id) {
        sortMapper.deleteById(id);
        return ResultUtil.success(1, "成功", null);
    }

    /**
     * 根据id集合批量删除类型接口实现类
     */
    @Override
    public Result<?> delBatchSortInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        sortMapper.deleteBatchIds(list);
        return ResultUtil.success(1, "成功", null);
    }
}