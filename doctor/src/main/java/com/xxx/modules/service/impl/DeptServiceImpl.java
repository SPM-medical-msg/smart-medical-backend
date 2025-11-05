package com.xxx.modules.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.modules.mapper.DeptMapper;
import com.xxx.modules.entity.Dept;
import com.xxx.modules.service.DeptService;
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
 * 科室
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Autowired
    private DeptMapper deptMapper;



    /**
     *  获取所有科室接口实现类
     * @param pageNum
     * @param pageSize
     * @param dept
     * @return
     */
    @Override
    public Result<?> selectDeptList(Dept dept, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        if (dept.getDeptName() !=null){
            wrapper.like("dept_name",dept.getDeptName());
        }
        List<Dept> data = deptMapper.selectList(wrapper);
        return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }


    /**
     * 获取单个科室接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> selectDeptInfo(Integer id) {
        Dept dept = deptMapper.selectById(id);
        return ResultUtil.success(1,"成功",dept);
    }

    /**
     * 保存科室接口实现类
     * @param dept
     * @return
     */
    @Override
    public Result<?> saveDeptInfo(Dept dept) {
        dept.setCreateTime(TimeUtil.getCurrentTime());
        dept.setUpdateTime(TimeUtil.getCurrentTime());
        deptMapper.insert(dept);
        return ResultUtil.success(1,"成功",null);
}

    /**
     * 更新科室接口实现类
     * @param dept
     * @return
     */
    @Override
    public Result<?> updateDeptInfo(Dept dept) {
        dept.setUpdateTime(TimeUtil.getCurrentTime());
        deptMapper.updateById(dept);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除科室删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> delDeptInfo(Integer id) {
        deptMapper.deleteById(id);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除科室接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result<?> delBatchDeptInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        deptMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }

}