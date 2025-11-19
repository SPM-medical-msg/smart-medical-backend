package com.xxx.modules.controller;;
import com.xxx.modules.service.ExchangeService;
import com.xxx.modules.entity.Exchange;
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
 * 药品订单
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
@RestController
@RequestMapping("/common/exchange")
@Api(tags="药品订单")
public class ExchangeController {
    @Autowired
    private ExchangeService exchangeService;

    /**
    *  获取所有药品订单
    * @param pageNum
    * @param pageSize
    * @param exchange
    * @return
    */

    @GetMapping("/getExchangeList")
    @ApiOperation("获取所有药品订单")
    public Result<?> getExchangeList(Exchange exchange, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return exchangeService.selectExchangeList(exchange,pageNum,pageSize);
    }


    @GetMapping("/getExchangeInfo")
    @ApiOperation("根据id获取单个药品订单")
    public Result<?> getExchangeInfo(Integer id){
        return exchangeService.selectExchangeInfo(id);
    }

    /**
    * 保存药品订单
    * @param exchange
    * @return
    */
    @PostMapping("/saveExchangeInfo")
    @ApiOperation("保存药品订单")
    public Result<?> saveExchangeInfo(@RequestBody Exchange exchange){
        return exchangeService.saveExchangeInfo(exchange);
    }

    @PostMapping("/payExchangeInfo")
    @ApiOperation("保存药品订单")
    public Result<?> payExchangeInfo(@RequestBody Exchange exchange){
        return exchangeService.payExchangeInfo(exchange);
    }
    /**
     * 更新药品订单
     * @param exchange
     * @return
     */

    @PutMapping("/updateExchangeInfo")
    @ApiOperation("更新药品订单")
    public Result<?> updateExchangeInfo(@RequestBody Exchange exchange){
        return exchangeService.updateExchangeInfo(exchange);
    }

    /**
     * 根据id删除药品订单
     * @param id
     * @return
     */
    @DeleteMapping("/delExchangeInfo")
    @ApiOperation("根据id删除药品订单")
    public Result<?> delExchangeInfo(Integer id){
        return exchangeService.delExchangeInfo(id);
    }

    /**
     * 根据id集合批量删除药品订单
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchExchangeInfo")
    @ApiOperation("根据id集合批量删除药品订单")
    public Result<?> delBatchExchangeInfo(String idList){
        return exchangeService.delBatchExchangeInfo(idList);
    }








}