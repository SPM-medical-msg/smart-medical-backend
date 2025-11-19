package com.xxx.modules.controller;;
import com.xxx.modules.service.EvaluateService;
import com.xxx.modules.entity.Evaluate;
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
 * 评论
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-04-05
 */
@RestController
@RequestMapping("/common/evaluate")
@Api(tags="评论")
public class EvaluateController {
    @Autowired
    private EvaluateService evaluateService;

    /**
    *  获取所有评论
    * @param pageNum
    * @param pageSize
    * @param evaluate
    * @return
    */

    @GetMapping("/getEvaluateList")
    @ApiOperation("获取所有评论")
    public Result<?> getEvaluateList(Evaluate evaluate, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return evaluateService.selectEvaluateList(evaluate,pageNum,pageSize);
    }


    @GetMapping("/getEvaluateInfo")
    @ApiOperation("根据id获取单个评论")
    public Result<?> getEvaluateInfo(Integer id){
        return evaluateService.selectEvaluateInfo(id);
    }

    /**
    * 保存评论
    * @param evaluate
    * @return
    */
    @PostMapping("/saveEvaluateInfo")
    @ApiOperation("保存评论")
    public Result<?> saveEvaluateInfo(@RequestBody Evaluate evaluate){
        return evaluateService.saveEvaluateInfo(evaluate);
    }


    /**
     * 更新评论
     * @param evaluate
     * @return
     */

    @PutMapping("/updateEvaluateInfo")
    @ApiOperation("更新评论")
    public Result<?> updateEvaluateInfo(@RequestBody Evaluate evaluate){
        return evaluateService.updateEvaluateInfo(evaluate);
    }

    /**
     * 根据id删除评论
     * @param id
     * @return
     */
    @DeleteMapping("/delEvaluateInfo")
    @ApiOperation("根据id删除评论")
    public Result<?> delEvaluateInfo(Integer id){
        return evaluateService.delEvaluateInfo(id);
    }

    /**
     * 根据id集合批量删除评论
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchEvaluateInfo")
    @ApiOperation("根据id集合批量删除评论")
    public Result<?> delBatchEvaluateInfo(String idList){
        return evaluateService.delBatchEvaluateInfo(idList);
    }








}