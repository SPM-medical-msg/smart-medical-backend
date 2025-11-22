package com.xxx.modules.controller;;
import com.xxx.modules.service.OrderService;
import com.xxx.modules.entity.Order;
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
 * 挂号
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@RestController
@RequestMapping("/common/order")
@Api(tags="挂号")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     *  获取所有挂号
     * @param pageNum
     * @param pageSize
     * @param order
     * @return
     */

    @GetMapping("/getOrderList")
    @ApiOperation("获取所有挂号")
    public Result<?> getOrderList(Order order, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                  @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return orderService.selectOrderList(order,pageNum,pageSize);
    }


    @GetMapping("/getOrderInfo")
    @ApiOperation("根据id获取单个挂号")
    public Result<?> getOrderInfo(Integer id){
        return orderService.selectOrderInfo(id);
    }

    /**
     * 保存挂号
     * @param order
     * @return
     */
    @PostMapping("/saveOrderInfo")
    @ApiOperation("保存挂号")
    public Result<?> saveOrderInfo(@RequestBody Order order){
        return orderService.saveOrderInfo(order);
    }


    /**
     * 更新挂号
     * @param order
     * @return
     */

    @PutMapping("/updateOrderInfo")
    @ApiOperation("更新挂号")
    public Result<?> updateOrderInfo(@RequestBody Order order){
        return orderService.updateOrderInfo(order);
    }

    /**
     * 根据id删除挂号
     * @param id
     * @return
     */
    @DeleteMapping("/delOrderInfo")
    @ApiOperation("根据id删除挂号")
    public Result<?> delOrderInfo(Integer id){
        return orderService.delOrderInfo(id);
    }

    /**
     * 根据id集合批量删除挂号
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchOrderInfo")
    @ApiOperation("根据id集合批量删除挂号")
    public Result<?> delBatchOrderInfo(String idList){
        return orderService.delBatchOrderInfo(idList);
    }

    @GetMapping("/getOrderStaData")
    public Result<?> getOrderStaData(Integer type){
        return orderService.selectOrderStaData(type);
    }
    @PostMapping("/payOrderInfo")
    public Result<?> payOrderInfo(@RequestBody Order order){
        return orderService.payOrderInfo(order);
    }

    /**
     * 更新订单状态
     * @param params 包含orderId和status的Map
     * @return
     */
    @PostMapping("/updateOrderStatus")
    @ApiOperation("更新订单状态")
    public Result<?> updateOrderStatus(@RequestBody Map<String, Integer> params) {
        Integer orderId = params.get("orderId");
        Integer status = params.get("status");
        return orderService.updateOrderStatus(orderId, status);
    }


    /**
     * 导出
     * @param order
     * @return
     */
    @PostMapping("/exportOrderExcel")
    public void export(HttpServletResponse response, Order order) {
        try{
            System.out.println("开始导出");
            List<Order> data = orderService.selectExcel(order);
            EasyPoiUtil.exportExcel(data, "挂号表", "导出sheet1",Order.class, "挂号信息表.xlsx", response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}