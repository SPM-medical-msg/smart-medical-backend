package com.xxx.modules.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.modules.entity.Friend;
import com.xxx.modules.entity.FriendMessage;
import com.xxx.modules.entity.User;
import com.xxx.modules.mapper.FriendMapper;
import com.xxx.modules.mapper.FriendMessageMapper;
import com.xxx.modules.mapper.UserMapper;
import com.xxx.modules.mqtt.MqttGateway;
import com.xxx.modules.service.FriendService;
import com.xxx.modules.utils.Result;
import com.xxx.modules.utils.ResultUtil;
import com.xxx.modules.utils.TimeUtil;
import org.apache.xmlbeans.impl.xb.substwsdl.TImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 好友
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-08-21
 */
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {

    @Autowired
    private FriendMapper friendMapper;
    @Autowired
    private FriendMessageMapper friendMessageMapper;



    /**
     *  获取所有好友接口实现类
     * @param pageNum
     * @param pageSize
     * @param friend
     * @return
     */
    @Override
    public Result selectFriendList(Friend friend, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Friend> data = friendMapper.selectListInfo(friend);
        for (Friend friend1:data){
            QueryWrapper<FriendMessage> wrapper = new QueryWrapper<>();
            wrapper.eq("send_user_id",friend1.getFriendId()).eq("receive_user_id",friend1.getUserId()).eq("is_view",1);
            Integer count = friendMessageMapper.selectCount(wrapper);
            friend1.setCount(count==null?0:count);

            QueryWrapper<Friend> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("user_id",friend1.getFriendId()).eq("friend_id",friend1.getUserId());
            Friend friend11 = friendMapper.selectOne(wrapper1);
            if (friend11 !=null){
                friend1.setUpdateId(friend11.getId());
            }
        }
       return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }

    @Autowired
    private UserMapper userMapper;


    /**
     * 获取单个好友接口实现类
     * @param id
     * @return
     */
    @Override
    public Result selectFriendInfo(Integer id) {
        Friend friend = friendMapper.selectById(id);
        User user = userMapper.selectById(friend.getUserId());
        if (user !=null){
            friend.setRealName(user.getRealName());
            friend.setSex(user.getSex());
            friend.setUserName(user.getUserName());
            friend.setImageUrl(user.getImageUrl());
        }
        return ResultUtil.success(1,"成功",friend);
    }

    /**
     * 保存好友接口实现类
     * @param friend
     * @return
     */
    @Override
    @Transactional
    public Result saveFriendInfo(Friend friend) {
        if (friend.getUserId().equals(friend.getFriendId())){
            return ResultUtil.error(-1,"不可添加自己为好友");
        }
        //双向绑定好友关系
        LambdaQueryWrapper<Friend> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Friend::getUserId,friend.getUserId()).eq(Friend::getFriendId,friend.getFriendId());
        Friend friend1 = friendMapper.selectOne(wrapper);

        LambdaQueryWrapper<Friend> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(Friend::getUserId,friend.getFriendId()).eq(Friend::getFriendId,friend.getUserId());
        Friend friend2 = friendMapper.selectOne(wrapper);
//        int count = this.count(wrapper);
        if (friend1 !=null && friend1.getStatus() == 2 && friend2 !=null && friend2.getStatus() == 2){
            return ResultUtil.error(-1,"你们已经是好友关系了");
        }
        if (friend1 !=null && friend1.getStatus() == 1 && friend2 !=null && friend2.getStatus() == 1){
            return ResultUtil.error(-1,"您已经发送好友申请,请耐心等待");
        }

        if (friend1 == null){
            friend.setCreateTime(TimeUtil.getCurrentTime());
            friend.setUpdateTime(TimeUtil.getCurrentTime());
            friend.setStatus(1);
            friend.setActiveAddUserId(friend.getUserId());
            friend.setAcceptAddUserId(friend.getFriendId());
            friend.setFriendType(1);
            friendMapper.insert(friend);
        }else {
            friend1.setUpdateTime(TimeUtil.getCurrentTime());
            friend1.setStatus(1);
            friendMapper.updateById(friend1);
        }

        if (friend2 == null){
            Friend friendNew = new Friend();
            friendNew.setUserId(friend.getFriendId());
            friendNew.setFriendId(friend.getUserId());
            friendNew.setStatus(1);
            friendNew.setFriendType(1);
            friendNew.setApplyMessage(friend.getApplyMessage());
            friendNew.setActiveAddUserId(friend.getUserId());
            friendNew.setAcceptAddUserId(friend.getFriendId());
            friendNew.setCreateTime(TimeUtil.getCurrentTime());
            friendNew.setUpdateTime(TimeUtil.getCurrentTime());
            friendMapper.insert(friendNew);
        }else {
            friend2.setStatus(1);
            friend2.setUpdateTime(TimeUtil.getCurrentTime());
            friendMapper.updateById(friend2);
        }



        JSONObject jsonObject = new JSONObject();
        User user = userMapper.selectById(friend.getFriendId());

        jsonObject.put("msg","用户"+user.getRealName()+"您好,您有一条好友申请");
        mqttGateway.sendToMqtt("ADD/APPLY/"+friend.getFriendId(),jsonObject.toString());
        return ResultUtil.success(1,"成功",null);
}

    @Autowired
    private MqttGateway mqttGateway;

    /**
     * 更新好友接口实现类
     * @param friend
     * @return
     */
    @Override
    public Result updateFriendInfo(Friend friend) {
           friend.setUpdateTime(TimeUtil.getCurrentTime());
           friendMapper.updateById(friend);

        Friend friend2 = friendMapper.selectById(friend.getId());
        QueryWrapper<Friend> wrapper = new QueryWrapper<>();
           wrapper.eq("user_id",friend2.getFriendId()).eq("friend_id",friend2.getUserId());
           Friend friend1 = new Friend();
           friend1.setStatus(friend.getStatus());
           friend1.setUpdateTime(TimeUtil.getCurrentTime());
           friendMapper.update(friend1,wrapper);

           //给加好友的人发送信息

        User user = userMapper.selectById(friend2.getUserId());
        User user1 = userMapper.selectById(friend2.getFriendId());
        JSONObject jsonObject = new JSONObject();
        Integer status = friend.getStatus();
        String str = "";
        if (status == 2){
            str = "通过";
        }
        if (status ==3){
            str = "拒绝";
        }
        if (status == 4){
            str = "忽略";
        }
        String msg = user.getRealName() +"您好,您给"+user1.getRealName()+"发送的好友申请已"+str;
        jsonObject.put("msg",msg);
        mqttGateway.sendToMqtt("PASS/APPLY/"+user.getId(),jsonObject.toString());

        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除好友删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result delFriendInfo(Integer id) {
        Friend friend1 = friendMapper.selectById(id);
        QueryWrapper<Friend> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",friend1.getUserId()).eq("friend_id",friend1.getFriendId());
        friendMapper.delete(wrapper);

        QueryWrapper<Friend> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("user_id",friend1.getFriendId()).eq("friend_id",friend1.getUserId());
        friendMapper.delete(wrapper2);


        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除好友接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result delBatchFriendInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        friendMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }

    @Override
    public Result selectApplyFriendList(Friend friend, Integer pageNum, Integer pageSize) {
        List<Friend> data = friendMapper.selectApplyFriendList(friend);
        return ResultUtil.success(1,"正常",data);
    }

    @Override
    public Result updateFriendInfo2(Friend friend) {
        QueryWrapper<Friend> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",friend.getUserId()).eq("friend_id",friend.getFriendId());
        Friend friend1 = new Friend();
        friend1.setFriendType(friend.getFriendType());
        friendMapper.update(friend1,wrapper);
//        friendMapper.updateById(friend);
        return ResultUtil.success(1,"正常",null);
    }

    @Override
    public Result selectFriendInfoById(Friend friend) {
//        QueryWrapper<Friend> wrapper = new QueryWrapper<>();
//        wrapper.eq("user_id",friend.getUserId()).eq("friend_id",friend.getFriendId());
//        Friend friend1 = friendMapper.selectById(friend.getId());

        QueryWrapper<Friend> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("user_id",friend.getUserId()).eq("friend_id",friend.getFriendId());
        Friend friend1 = friendMapper.selectOne(wrapper1);
        if (friend1 !=null){
            User user = userMapper.selectById(friend1.getFriendId());
            if (user !=null){
                friend1.setRealName(user.getRealName());
                friend1.setSex(user.getSex());
                friend1.setUserName(user.getUserName());
                friend1.setImageUrl(user.getImageUrl());
            }
        }


        return ResultUtil.success(1,"成功",friend1);
    }

}