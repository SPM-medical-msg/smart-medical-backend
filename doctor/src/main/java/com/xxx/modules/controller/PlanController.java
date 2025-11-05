package com.xxx.modules.controller;;
import com.xxx.modules.service.PlanService;
import com.xxx.modules.entity.Plan;
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
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
/**
 * 排班
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@RestController
@RequestMapping("/common/plan")
@Api(tags="排班")
public class PlanController {
    @Autowired
    private PlanService planService;

    /**
    *  获取所有排班
    * @param pageNum
    * @param pageSize
    * @param plan
    * @return
    */

    @GetMapping("/getPlanList")
    @ApiOperation("获取所有排班")
    public Result<?> getPlanList(Plan plan, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return planService.selectPlanList(plan,pageNum,pageSize);
    }


    @GetMapping("/getPlanInfo")
    @ApiOperation("根据id获取单个排班")
    public Result<?> getPlanInfo(Integer id){
        return planService.selectPlanInfo(id);
    }

    /**
    * 保存排班
    * @param plan
    * @return
    */
    @PostMapping("/savePlanInfo")
    @ApiOperation("保存排班")
    public Result<?> savePlanInfo(@RequestBody Plan plan){
        return planService.savePlanInfo(plan);
    }


    /**
     * 更新排班
     * @param plan
     * @return
     */

    @PutMapping("/updatePlanInfo")
    @ApiOperation("更新排班")
    public Result<?> updatePlanInfo(@RequestBody Plan plan){
        return planService.updatePlanInfo(plan);
    }

    /**
     * 根据id删除排班
     * @param id
     * @return
     */
    @DeleteMapping("/delPlanInfo")
    @ApiOperation("根据id删除排班")
    public Result<?> delPlanInfo(Integer id){
        return planService.delPlanInfo(id);
    }

    /**
     * 根据id集合批量删除排班
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchPlanInfo")
    @ApiOperation("根据id集合批量删除排班")
    public Result<?> delBatchPlanInfo(String idList){
        return planService.delBatchPlanInfo(idList);
    }





        /**
         * 导出
         * @param plan
         * @return
         */
    @PostMapping("/exportPlanExcel")
    public void export(HttpServletResponse response, Plan plan) {
        try{
            System.out.println("开始导出");
            List<Plan> data = planService.selectExcel(plan);
            EasyPoiUtil.exportExcel(data, "排班表", "导出sheet1",Plan.class, "排班信息表.xlsx", response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}