package com.xxx.modules.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.entity.Friend;
import com.xxx.modules.utils.Result;
/**
 * 好友
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-08-21
 */
public interface FriendService extends IService<Friend>{

    /**
     *  获取所有好友接口
     * @param pageNum
     * @param pageSize
     * @param friend
     * @return
     */
    Result selectFriendList(Friend friend, Integer pageNum, Integer pageSize);


    /**
     *  获取单个好友接口
     * @param id
     * @return
     */
    Result selectFriendInfo(Integer id);

    /**
     * 保存好友接口
     * @param friend
     * @return
     */
    Result saveFriendInfo(Friend friend);

    /**
     * 更新好友接口
     * @param friend
     * @return
     */
    Result updateFriendInfo(Friend friend);

    /**
     * 根据id删除好友接口
     * @param id
     * @return
     */
    Result delFriendInfo(Integer id);

    /**
     * 根据id集合批量删除好友接口
     * @param idList
     * @return
     */
    Result delBatchFriendInfo(String idList);


    Result selectApplyFriendList(Friend friend, Integer pageNum, Integer pageSize);

    Result updateFriendInfo2(Friend friend);

    Result selectFriendInfoById(Friend friend);
}