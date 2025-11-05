package com.xxx.modules.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.modules.entity.Diagnose;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * 诊断
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@Mapper
public interface DiagnoseMapper extends BaseMapper<Diagnose> {

    List<Diagnose> selectListInfo(Diagnose diagnose);
}