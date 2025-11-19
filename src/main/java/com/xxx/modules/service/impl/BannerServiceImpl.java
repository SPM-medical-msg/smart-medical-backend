package com.xxx.modules.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.modules.mapper.BannerMapper;
import com.xxx.modules.entity.Banner;
import com.xxx.modules.service.BannerService;
import com.xxx.modules.entity.User;
import com.xxx.modules.mapper.UserMapper;
import com.xxx.modules.utils.Result;
import com.xxx.modules.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import com.xxx.modules.utils.TimeUtil;

/**
 * 轮播图
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;



    /**
     *  获取所有轮播图接口实现类
     * @param pageNum
     * @param pageSize
     * @param banner
     * @return
     */
    @Override
    public Result<?> selectBannerList(Banner banner, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<Banner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        if (banner.getTitle() !=null){
            wrapper.like("title",banner.getTitle());
        }
        if (banner.getStatus() !=null){
            wrapper.eq("status",banner.getStatus());
        }
        List<Banner> data = bannerMapper.selectList(wrapper);
        return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }


    /**
     * 获取单个轮播图接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> selectBannerInfo(Integer id) {
        Banner banner = bannerMapper.selectById(id);
        return ResultUtil.success(1,"成功",banner);
    }

    /**
     * 保存轮播图接口实现类
     * @param banner
     * @return
     */
    @Override
    public Result<?> saveBannerInfo(Banner banner) {
        banner.setCreateTime(TimeUtil.getCurrentTime());
        banner.setUpdateTime(TimeUtil.getCurrentTime());
        bannerMapper.insert(banner);
        return ResultUtil.success(1,"成功",null);
}

    /**
     * 更新轮播图接口实现类
     * @param banner
     * @return
     */
    @Override
    public Result<?> updateBannerInfo(Banner banner) {
        banner.setUpdateTime(TimeUtil.getCurrentTime());
        bannerMapper.updateById(banner);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除轮播图删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result<?> delBannerInfo(Integer id) {
        bannerMapper.deleteById(id);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除轮播图接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result<?> delBatchBannerInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        bannerMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }

}