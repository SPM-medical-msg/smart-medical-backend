package com.xxx.modules.controller;

import com.xxx.modules.entity.FriendMessage;
import com.xxx.modules.service.FriendMessageService;
import com.xxx.modules.utils.EasyPoiUtil;
import com.xxx.modules.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

;
/**
 * 好友消息
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-08-22
 */
@RestController
@RequestMapping("/common/friendMessage")
@Api(tags="好友消息")
public class FriendMessageController {
    @Autowired
    private FriendMessageService friendMessageService;

    /**
    *  获取所有好友消息
    * @param pageNum
    * @param pageSize
    * @param friendMessage
    * @return
    */

    @GetMapping("/getFriendMessageList")
    @ApiOperation("获取所有好友消息")
    public Result getFriendMessageList(FriendMessage friendMessage, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return friendMessageService.selectFriendMessageList(friendMessage,pageNum,pageSize);
    }


    @GetMapping("/getFriendMessageInfo")
    @ApiOperation("获取聊天记录")
    public Result getFriendMessageInfo(FriendMessage friendMessage, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                       @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return friendMessageService.selectFriendMessageInfo(friendMessage,pageNum,pageSize);
    }

    /**
    * 保存好友消息
    * @param friendMessage
    * @return
    */
    @PostMapping("/saveFriendMessageInfo")
    @ApiOperation("保存好友消息")
    public Result saveFriendMessageInfo(@RequestBody FriendMessage friendMessage){
        return friendMessageService.saveFriendMessageInfo(friendMessage);
    }


    /**
     * 更新好友消息
     * @param friendMessage
     * @return
     */

    @PutMapping("/updateFriendMessageInfo")
    @ApiOperation("更新好友消息")
    public Result updateFriendMessageInfo(@RequestBody FriendMessage friendMessage){
        return friendMessageService.updateFriendMessageInfo(friendMessage);
    }

    /**
     * 根据id删除好友消息
     * @param id
     * @return
     */
    @DeleteMapping("/delFriendMessageInfo")
    @ApiOperation("根据id删除好友消息")
    public Result delFriendMessageInfo(Integer id){
        return friendMessageService.delFriendMessageInfo(id);
    }

    /**
     * 根据id集合批量删除好友消息
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchFriendMessageInfo")
    @ApiOperation("根据id集合批量删除好友消息")
    public Result delBatchFriendMessageInfo(String idList){
        return friendMessageService.delBatchFriendMessageInfo(idList);
    }





        /**
         * 导出
         * @param friendMessage
         * @return
         */
    @PostMapping("/exportFriendMessageExcel")
    public void export(HttpServletResponse response, FriendMessage friendMessage) {
        try{
            System.out.println("开始导出");
            List<FriendMessage> data = friendMessageService.selectExcel(friendMessage);
            EasyPoiUtil.exportExcel(data, "好友消息表", "导出sheet1",FriendMessage.class, "好友消息信息表.xlsx", response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}