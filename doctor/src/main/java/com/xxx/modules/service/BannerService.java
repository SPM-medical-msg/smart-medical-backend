package com.xxx.modules.service;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.utils.Result;
import com.xxx.modules.entity.Banner;
import java.util.List;
/**
 * 轮播图
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
public interface BannerService extends IService<Banner>{

    /**
     *  获取所有轮播图接口
     * @param pageNum
     * @param pageSize
     * @param banner
     * @return
     */
    Result <?>selectBannerList(Banner banner, Integer pageNum, Integer pageSize);


    /**
     *  获取单个轮播图接口
     * @param id
     * @return
     */
    Result<?> selectBannerInfo(Integer id);

    /**
     * 保存轮播图接口
     * @param banner
     * @return
     */
    Result<?> saveBannerInfo(Banner banner);

    /**
     * 更新轮播图接口
     * @param banner
     * @return
     */
    Result<?> updateBannerInfo(Banner banner);

    /**
     * 根据id删除轮播图接口
     * @param id
     * @return
     */
    Result<?> delBannerInfo(Integer id);

    /**
     * 根据id集合批量删除轮播图接口
     * @param idList
     * @return
     */
    Result<?> delBatchBannerInfo(String idList);




}