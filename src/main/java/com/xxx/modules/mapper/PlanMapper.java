package com.xxx.modules.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.modules.entity.Plan;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * 排班
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@Mapper
public interface PlanMapper extends BaseMapper<Plan> {

    List<Plan> selectListInfo(Plan plan);
}