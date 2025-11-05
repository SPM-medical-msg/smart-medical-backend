package com.xxx.modules.controller;;
import com.xxx.modules.service.BannerService;
import com.xxx.modules.entity.Banner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import com.github.pagehelper.PageInfo;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.xxx.modules.utils.Result;
import com.xxx.modules.utils.ResultUtil;
import com.xxx.modules.utils.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
/**
 * 轮播图
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@RestController
@RequestMapping("/common/banner")
@Api(tags="轮播图")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    /**
    *  获取所有轮播图
    * @param pageNum
    * @param pageSize
    * @param banner
    * @return
    */

    @GetMapping("/getBannerList")
    @ApiOperation("获取所有轮播图")
    public Result<?> getBannerList(Banner banner, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return bannerService.selectBannerList(banner,pageNum,pageSize);
    }


    @GetMapping("/getBannerInfo")
    @ApiOperation("根据id获取单个轮播图")
    public Result<?> getBannerInfo(Integer id){
        return bannerService.selectBannerInfo(id);
    }

    /**
    * 保存轮播图
    * @param banner
    * @return
    */
    @PostMapping("/saveBannerInfo")
    @ApiOperation("保存轮播图")
    public Result<?> saveBannerInfo(@RequestBody Banner banner){
        return bannerService.saveBannerInfo(banner);
    }


    /**
     * 更新轮播图
     * @param banner
     * @return
     */

    @PutMapping("/updateBannerInfo")
    @ApiOperation("更新轮播图")
    public Result<?> updateBannerInfo(@RequestBody Banner banner){
        return bannerService.updateBannerInfo(banner);
    }

    /**
     * 根据id删除轮播图
     * @param id
     * @return
     */
    @DeleteMapping("/delBannerInfo")
    @ApiOperation("根据id删除轮播图")
    public Result<?> delBannerInfo(Integer id){
        return bannerService.delBannerInfo(id);
    }

    /**
     * 根据id集合批量删除轮播图
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchBannerInfo")
    @ApiOperation("根据id集合批量删除轮播图")
    public Result<?> delBatchBannerInfo(String idList){
        return bannerService.delBatchBannerInfo(idList);
    }








}