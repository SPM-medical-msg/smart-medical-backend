package com.xxx.modules.controller;;
import com.xxx.modules.service.DiagnoseService;
import com.xxx.modules.entity.Diagnose;
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
 * 诊断
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@RestController
@RequestMapping("/common/diagnose")
@Api(tags="诊断")
public class DiagnoseController {
    @Autowired
    private DiagnoseService diagnoseService;

    /**
    *  获取所有诊断
    * @param pageNum
    * @param pageSize
    * @param diagnose
    * @return
    */

    @GetMapping("/getDiagnoseList")
    @ApiOperation("获取所有诊断")
    public Result<?> getDiagnoseList(Diagnose diagnose, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return diagnoseService.selectDiagnoseList(diagnose,pageNum,pageSize);
    }


    @GetMapping("/getDiagnoseInfo")
    @ApiOperation("根据id获取单个诊断")
    public Result<?> getDiagnoseInfo(Integer id){
        return diagnoseService.selectDiagnoseInfo(id);
    }

    /**
    * 保存诊断
    * @param diagnose
    * @return
    */
    @PostMapping("/saveDiagnoseInfo")
    @ApiOperation("保存诊断")
    public Result<?> saveDiagnoseInfo(@RequestBody Diagnose diagnose){
        return diagnoseService.saveDiagnoseInfo(diagnose);
    }


    /**
     * 更新诊断
     * @param diagnose
     * @return
     */

    @PutMapping("/updateDiagnoseInfo")
    @ApiOperation("更新诊断")
    public Result<?> updateDiagnoseInfo(@RequestBody Diagnose diagnose){
        return diagnoseService.updateDiagnoseInfo(diagnose);
    }

    /**
     * 根据id删除诊断
     * @param id
     * @return
     */
    @DeleteMapping("/delDiagnoseInfo")
    @ApiOperation("根据id删除诊断")
    public Result<?> delDiagnoseInfo(Integer id){
        return diagnoseService.delDiagnoseInfo(id);
    }

    /**
     * 根据id集合批量删除诊断
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchDiagnoseInfo")
    @ApiOperation("根据id集合批量删除诊断")
    public Result<?> delBatchDiagnoseInfo(String idList){
        return diagnoseService.delBatchDiagnoseInfo(idList);
    }








}