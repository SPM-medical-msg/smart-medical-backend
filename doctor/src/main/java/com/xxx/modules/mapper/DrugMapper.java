package com.xxx.modules.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.modules.entity.Drug;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * 药品
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
@Mapper
public interface DrugMapper extends BaseMapper<Drug> {

    List<Drug> selectListInfo(Drug drug);
}