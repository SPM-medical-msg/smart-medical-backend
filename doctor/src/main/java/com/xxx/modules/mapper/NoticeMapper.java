package com.xxx.modules.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.modules.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * 公告
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-01-20
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

}