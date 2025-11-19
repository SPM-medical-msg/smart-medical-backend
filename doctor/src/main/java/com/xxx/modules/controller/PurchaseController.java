package com.xxx.modules.controller;;
import com.xxx.modules.service.PurchaseService;
import com.xxx.modules.entity.Purchase;
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
 * 采购
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
@RestController
@RequestMapping("/common/purchase")
@Api(tags="采购")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    /**
    *  获取所有采购
    * @param pageNum
    * @param pageSize
    * @param purchase
    * @return
    */

    @GetMapping("/getPurchaseList")
    @ApiOperation("获取所有采购")
    public Result<?> getPurchaseList(Purchase purchase, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return purchaseService.selectPurchaseList(purchase,pageNum,pageSize);
    }


    @GetMapping("/getPurchaseInfo")
    @ApiOperation("根据id获取单个采购")
    public Result<?> getPurchaseInfo(Integer id){
        return purchaseService.selectPurchaseInfo(id);
    }

    /**
    * 保存采购
    * @param purchase
    * @return
    */
    @PostMapping("/savePurchaseInfo")
    @ApiOperation("保存采购")
    public Result<?> savePurchaseInfo(@RequestBody Purchase purchase){
        return purchaseService.savePurchaseInfo(purchase);
    }


    /**
     * 更新采购
     * @param purchase
     * @return
     */

    @PutMapping("/updatePurchaseInfo")
    @ApiOperation("更新采购")
    public Result<?> updatePurchaseInfo(@RequestBody Purchase purchase){
        return purchaseService.updatePurchaseInfo(purchase);
    }

    /**
     * 根据id删除采购
     * @param id
     * @return
     */
    @DeleteMapping("/delPurchaseInfo")
    @ApiOperation("根据id删除采购")
    public Result<?> delPurchaseInfo(Integer id){
        return purchaseService.delPurchaseInfo(id);
    }

    /**
     * 根据id集合批量删除采购
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchPurchaseInfo")
    @ApiOperation("根据id集合批量删除采购")
    public Result<?> delBatchPurchaseInfo(String idList){
        return purchaseService.delBatchPurchaseInfo(idList);
    }








}