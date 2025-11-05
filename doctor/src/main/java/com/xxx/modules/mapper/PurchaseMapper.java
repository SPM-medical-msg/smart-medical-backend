package com.xxx.modules.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.modules.entity.Purchase;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * 采购
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
@Mapper
public interface PurchaseMapper extends BaseMapper<Purchase> {

    List<Purchase> selectListInfo(Purchase purchase);
}