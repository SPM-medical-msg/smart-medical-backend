package com.xxx.modules.controller;;
import com.xxx.modules.service.SortService;
import com.xxx.modules.entity.Sort;
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
 * 类型
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-12-19
 */
@RestController
@RequestMapping("/common/sort")
@Api(tags="类型")
public class SortController {
    @Autowired
    private SortService sortService;

    /**
    *  获取所有类型
    * @param pageNum
    * @param pageSize
    * @param sort
    * @return
    */

    @GetMapping("/getSortList")
    @ApiOperation("获取所有类型")
    public Result<?> getSortList(Sort sort, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return sortService.selectSortList(sort,pageNum,pageSize);
    }


    @GetMapping("/getSortInfo")
    @ApiOperation("根据id获取单个类型")
    public Result<?> getSortInfo(Integer id){
        return sortService.selectSortInfo(id);
    }

    /**
    * 保存类型
    * @param sort
    * @return
    */
    @PostMapping("/saveSortInfo")
    @ApiOperation("保存类型")
    public Result<?> saveSortInfo(@RequestBody Sort sort){
        return sortService.saveSortInfo(sort);
    }


    /**
     * 更新类型
     * @param sort
     * @return
     */

    @PutMapping("/updateSortInfo")
    @ApiOperation("更新类型")
    public Result<?> updateSortInfo(@RequestBody Sort sort){
        return sortService.updateSortInfo(sort);
    }

    /**
     * 根据id删除类型
     * @param id
     * @return
     */
    @DeleteMapping("/delSortInfo")
    @ApiOperation("根据id删除类型")
    public Result<?> delSortInfo(Integer id){
        return sortService.delSortInfo(id);
    }

    /**
     * 根据id集合批量删除类型
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchSortInfo")
    @ApiOperation("根据id集合批量删除类型")
    public Result<?> delBatchSortInfo(String idList){
        return sortService.delBatchSortInfo(idList);
    }








}