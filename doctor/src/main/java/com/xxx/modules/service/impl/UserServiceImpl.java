package com.xxx.modules.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.modules.mapper.EvaluateMapper;
import com.xxx.modules.mapper.UserMapper;
import com.xxx.modules.entity.User;
import com.xxx.modules.service.UserService;
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
 * 用户
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录接口(根据用户名和密码)
     * @param user
     * @return
     */
    @Override
    public Result<?> login(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",user.getUserName()).eq("password",user.getPassword()).eq("user_type",user.getUserType());
        User userFromDb = userMapper.selectOne(wrapper);
        if (userFromDb == null){
            return ResultUtil.error(-1,"登录失败,用户名或者密码错误");
        }else {
            return ResultUtil.success(1,"登录成功",userFromDb);
        }
    }

    @Override
    public Result<?> updatePassword(User user) {
        if (user.getPassword() == null){
            return ResultUtil.error(-1,"密码不得为空");
        }
        userMapper.updateById(user);
        return ResultUtil.success(1,"修改成功",null);
    }

    @Override
    public Result userRecharge(User user) {
        if (user.getMoney() == null){
            return ResultUtil.error(-1,"充值金额不得为空");
        }
        User user1 = userMapper.selectById(user.getId());
        if (user1.getMoney() == null){
            user1.setMoney(0.0);
        }
        user1.setMoney(user1.getMoney() + user.getMoney());
        userMapper.updateById(user1);
        return ResultUtil.success(1,"正常",null);
    }



    /**
     *  获取所有用户接口实现类
     * @param pageNum
     * @param pageSize
     * @param user
     * @return
     */
    @Override
    public Result<?> selectUserList(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> data = userMapper.selectListInfo(user);
        for (User user1:data){
            Double avgScore = evaluateMapper.selectAvgScore(user1.getId());
            user1.setScore(avgScore == null?0:avgScore);
        }
        return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }

    @Autowired
    private EvaluateMapper evaluateMapper;

    /**
     * 获取单个用户接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> selectUserInfo(Integer id) {
        User user = userMapper.selectById(id);
        if (user != null) {
            Double avgScore = evaluateMapper.selectAvgScore(user.getId());
            user.setScore(avgScore == null ? 0 : avgScore);
        }
        return ResultUtil.success(1, "成功", user);
    }

    /**
     * 保存用户接口实现类
     * @param user
     * @return
     */
    @Override
    public Result<?> saveUserInfo(User user) {
        user.setMoney(0.0);
        QueryWrapper<User> userNameWrapper = new QueryWrapper<>();
        userNameWrapper.eq("user_name",user.getUserName());
        User userNameInfo = userMapper.selectOne(userNameWrapper);
        if (userNameInfo != null){
            return ResultUtil.error(-1,"用户名重复");
        }
        user.setCreateTime(TimeUtil.getCurrentTime());
        user.setUpdateTime(TimeUtil.getCurrentTime());
        userMapper.insert(user);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 更新用户接口实现类
     * @param user
     * @return
     */
    @Override
    public Result<?> updateUserInfo(User user) {
        QueryWrapper<User> userNameWrapper = new QueryWrapper<>();
        userNameWrapper.eq("user_name",user.getUserName()).ne("id",user.getId());
        User userNameInfo = userMapper.selectOne(userNameWrapper);
        if (userNameInfo != null){
            return ResultUtil.error(-1,"用户名重复");
        }
        user.setUpdateTime(TimeUtil.getCurrentTime());
        userMapper.updateById(user);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除用户删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> delUserInfo(Integer id) {
        userMapper.deleteById(id);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除用户接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result<?> delBatchUserInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        userMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }

}