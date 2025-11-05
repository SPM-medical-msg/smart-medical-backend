package com.xxx.modules.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.modules.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * 用户
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> selectListInfo(User user);
}