package com.xxx.modules.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.modules.entity.FriendMessage;
import com.xxx.modules.mapper.FriendMessageMapper;
import com.xxx.modules.service.FriendMessageService;
import com.xxx.modules.utils.Result;
import com.xxx.modules.utils.ResultUtil;
import com.xxx.modules.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 好友消息
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-08-22
 */
@Service
public class FriendMessageServiceImpl extends ServiceImpl<FriendMessageMapper, FriendMessage> implements FriendMessageService {

    @Autowired
    private FriendMessageMapper friendMessageMapper;



    /**
     *  获取所有好友消息接口实现类
     * @param pageNum
     * @param pageSize
     * @param friendMessage
     * @return
     */
    @Override
    public Result selectFriendMessageList(FriendMessage friendMessage, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        List<FriendMessage> data = friendMessageMapper.selectListInfo(friendMessage);
       return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }


    /**
     * 获取好友消息接口实现类
     * @param id
     * @return
     */
    @Override
    public Result selectFriendMessageInfo(FriendMessage friendMessage,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        LambdaQueryWrapper<FriendMessage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FriendMessage::getSendUserId,friendMessage.getReceiveUserId()).eq(FriendMessage::getReceiveUserId,friendMessage.getSendUserId());
        FriendMessage newFriendMessage = new FriendMessage();
        newFriendMessage.setIsView(2);
        friendMessageMapper.update(newFriendMessage,wrapper);
        List<FriendMessage> friendMessageList = friendMessageMapper.selectMessageList(friendMessage);
        return ResultUtil.success(1,"成功",new PageInfo<>(friendMessageList));
    }

    /**
     * 保存好友消息接口实现类
     * @param friendMessage
     * @return
     */
    @Override
    public Result saveFriendMessageInfo(FriendMessage friendMessage) {
        friendMessage.setCreateTime(TimeUtil.getCurrentTime());
        friendMessage.setUpdateTime(TimeUtil.getCurrentTime());
        friendMessageMapper.insert(friendMessage);
        return ResultUtil.success(1,"成功",null);
}

    /**
     * 更新好友消息接口实现类
     * @param friendMessage
     * @return
     */
    @Override
    public Result updateFriendMessageInfo(FriendMessage friendMessage) {
        friendMessage.setUpdateTime(TimeUtil.getCurrentTime());
        friendMessageMapper.updateById(friendMessage);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除好友消息删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result delFriendMessageInfo(Integer id) {
        friendMessageMapper.deleteById(id);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除好友消息接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result delBatchFriendMessageInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        friendMessageMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 导出
     * @param friendMessage
     * @return
     */
    @Override
    public List<FriendMessage> selectExcel(FriendMessage friendMessage) {
        return friendMessageMapper.selectListInfo(friendMessage);
    }







}