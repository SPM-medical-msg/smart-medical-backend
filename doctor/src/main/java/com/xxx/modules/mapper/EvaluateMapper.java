package com.xxx.modules.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.modules.entity.Evaluate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 评论
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-04-05
 */
@Mapper
public interface EvaluateMapper extends BaseMapper<Evaluate> {

    List<Evaluate> selectListInfo(Evaluate evaluate);

    Double selectAvgScore(@Param("userId") Integer id);
}