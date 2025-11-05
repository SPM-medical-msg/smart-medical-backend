package com.xxx.modules.controller;;
import com.xxx.modules.service.DrugService;
import com.xxx.modules.entity.Drug;
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
 * 药品
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
@RestController
@RequestMapping("/common/drug")
@Api(tags="药品")
public class DrugController {
    @Autowired
    private DrugService drugService;

    /**
    *  获取所有药品
    * @param pageNum
    * @param pageSize
    * @param drug
    * @return
    */

    @GetMapping("/getDrugList")
    @ApiOperation("获取所有药品")
    public Result<?> getDrugList(Drug drug, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return drugService.selectDrugList(drug,pageNum,pageSize);
    }


    @GetMapping("/getDrugInfo")
    @ApiOperation("根据id获取单个药品")
    public Result<?> getDrugInfo(Integer id){
        return drugService.selectDrugInfo(id);
    }

    /**
    * 保存药品
    * @param drug
    * @return
    */
    @PostMapping("/saveDrugInfo")
    @ApiOperation("保存药品")
    public Result<?> saveDrugInfo(@RequestBody Drug drug){
        return drugService.saveDrugInfo(drug);
    }

    /**
     * 更新药品
     * @param drug
     * @return
     */

    @PutMapping("/updateDrugInfo")
    @ApiOperation("更新药品")
    public Result<?> updateDrugInfo(@RequestBody Drug drug){
        return drugService.updateDrugInfo(drug);
    }

    /**
     * 根据id删除药品
     * @param id
     * @return
     */
    @DeleteMapping("/delDrugInfo")
    @ApiOperation("根据id删除药品")
    public Result<?> delDrugInfo(Integer id){
        return drugService.delDrugInfo(id);
    }

    /**
     * 根据id集合批量删除药品
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchDrugInfo")
    @ApiOperation("根据id集合批量删除药品")
    public Result<?> delBatchDrugInfo(String idList){
        return drugService.delBatchDrugInfo(idList);
    }








}