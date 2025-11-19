package com.xxx.modules.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.entity.FriendMessage;
import com.xxx.modules.utils.Result;

import java.util.List;
/**
 * 好友消息
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-08-22
 */
public interface FriendMessageService extends IService<FriendMessage>{

    /**
     *  获取所有好友消息接口
     * @param pageNum
     * @param pageSize
     * @param friendMessage
     * @return
     */
    Result selectFriendMessageList(FriendMessage friendMessage, Integer pageNum, Integer pageSize);


    /**
     *  获取好友消息接口
     * @param friendMessage
     * @return
     */
    Result selectFriendMessageInfo(FriendMessage friendMessage,Integer pageNum,Integer pageSize);

    /**
     * 保存好友消息接口
     * @param friendMessage
     * @return
     */
    Result saveFriendMessageInfo(FriendMessage friendMessage);

    /**
     * 更新好友消息接口
     * @param friendMessage
     * @return
     */
    Result updateFriendMessageInfo(FriendMessage friendMessage);

    /**
     * 根据id删除好友消息接口
     * @param id
     * @return
     */
    Result delFriendMessageInfo(Integer id);

    /**
     * 根据id集合批量删除好友消息接口
     * @param idList
     * @return
     */
    Result delBatchFriendMessageInfo(String idList);



        /**
     * 导出
     * @param friendMessage
     * @return
     */
    List<FriendMessage> selectExcel(FriendMessage friendMessage);
    
    

}