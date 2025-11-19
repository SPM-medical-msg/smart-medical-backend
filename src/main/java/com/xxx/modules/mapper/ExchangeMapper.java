package com.xxx.modules.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.modules.entity.Exchange;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * 药品订单
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
@Mapper
public interface ExchangeMapper extends BaseMapper<Exchange> {

    List<Exchange> selectListInfo(Exchange exchange);
}