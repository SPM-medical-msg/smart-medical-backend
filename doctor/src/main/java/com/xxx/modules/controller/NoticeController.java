package com.xxx.modules.controller;;
import com.xxx.modules.service.NoticeService;
import com.xxx.modules.entity.Notice;
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
 * 公告
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-01-20
 */
@RestController
@RequestMapping("/common/notice")
@Api(tags="公告")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
    *  获取所有公告
    * @param pageNum
    * @param pageSize
    * @param notice
    * @return
    */

    @GetMapping("/getNoticeList")
    @ApiOperation("获取所有公告")
    public Result<?> getNoticeList(Notice notice, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return noticeService.selectNoticeList(notice,pageNum,pageSize);
    }


    @GetMapping("/getNoticeInfo")
    @ApiOperation("根据id获取单个公告")
    public Result<?> getNoticeInfo(Integer id){
        return noticeService.selectNoticeInfo(id);
    }

    /**
    * 保存公告
    * @param notice
    * @return
    */
    @PostMapping("/saveNoticeInfo")
    @ApiOperation("保存公告")
    public Result<?> saveNoticeInfo(@RequestBody Notice notice){
        return noticeService.saveNoticeInfo(notice);
    }


    /**
     * 更新公告
     * @param notice
     * @return
     */

    @PutMapping("/updateNoticeInfo")
    @ApiOperation("更新公告")
    public Result<?> updateNoticeInfo(@RequestBody Notice notice){
        return noticeService.updateNoticeInfo(notice);
    }

    /**
     * 根据id删除公告
     * @param id
     * @return
     */
    @DeleteMapping("/delNoticeInfo")
    @ApiOperation("根据id删除公告")
    public Result<?> delNoticeInfo(Integer id){
        return noticeService.delNoticeInfo(id);
    }

    /**
     * 根据id集合批量删除公告
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchNoticeInfo")
    @ApiOperation("根据id集合批量删除公告")
    public Result<?> delBatchNoticeInfo(String idList){
        return noticeService.delBatchNoticeInfo(idList);
    }








}