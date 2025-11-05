package com.xxx.modules.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.modules.entity.FriendMessage;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * 好友消息
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-08-22
 */
@Mapper
public interface FriendMessageMapper extends BaseMapper<FriendMessage> {

    List<FriendMessage> selectListInfo(FriendMessage friendMessage);

    List<FriendMessage> selectMessageList(FriendMessage friendMessage);

}