package com.xxx.modules.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.modules.entity.Friend;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * 好友
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-08-21
 */
@Mapper
public interface FriendMapper extends BaseMapper<Friend> {

    List<Friend> selectListInfo(Friend friend);

    List<Friend> selectApplyFriendList(Friend friend);

    List<Integer> selectDistinctUserId();

}