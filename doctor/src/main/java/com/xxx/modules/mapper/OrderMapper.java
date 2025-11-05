package com.xxx.modules.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.modules.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * 挂号
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> selectListInfo(Order order);
    Integer selectTotalCount(Order order);

    Double selectTotalPrice(Order order);

    List<Integer> selectDistinctUserId();

}