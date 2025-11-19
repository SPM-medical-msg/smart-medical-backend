package com.xxx.modules.controller;;
import com.xxx.modules.service.AlarmService;
import com.xxx.modules.entity.Alarm;
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
 * 报警
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
@RestController
@RequestMapping("/common/alarm")
@Api(tags="报警")
public class AlarmController {
    @Autowired
    private AlarmService alarmService;

    /**
    *  获取所有报警
    * @param pageNum
    * @param pageSize
    * @param alarm
    * @return
    */

    @GetMapping("/getAlarmList")
    @ApiOperation("获取所有报警")
    public Result<?> getAlarmList(Alarm alarm, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return alarmService.selectAlarmList(alarm,pageNum,pageSize);
    }


    @GetMapping("/getAlarmInfo")
    @ApiOperation("根据id获取单个报警")
    public Result<?> getAlarmInfo(Integer id){
        return alarmService.selectAlarmInfo(id);
    }

    /**
    * 保存报警
    * @param alarm
    * @return
    */
    @PostMapping("/saveAlarmInfo")
    @ApiOperation("保存报警")
    public Result<?> saveAlarmInfo(@RequestBody Alarm alarm){
        return alarmService.saveAlarmInfo(alarm);
    }


    /**
     * 更新报警
     * @param alarm
     * @return
     */

    @PutMapping("/updateAlarmInfo")
    @ApiOperation("更新报警")
    public Result<?> updateAlarmInfo(@RequestBody Alarm alarm){
        return alarmService.updateAlarmInfo(alarm);
    }

    /**
     * 根据id删除报警
     * @param id
     * @return
     */
    @DeleteMapping("/delAlarmInfo")
    @ApiOperation("根据id删除报警")
    public Result<?> delAlarmInfo(Integer id){
        return alarmService.delAlarmInfo(id);
    }

    /**
     * 根据id集合批量删除报警
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchAlarmInfo")
    @ApiOperation("根据id集合批量删除报警")
    public Result<?> delBatchAlarmInfo(String idList){
        return alarmService.delBatchAlarmInfo(idList);
    }








}