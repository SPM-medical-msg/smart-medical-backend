package com.xxx.modules.controller;import com.xxx.modules.entity.Friend;
import com.xxx.modules.service.FriendService;
import com.xxx.modules.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

;
/**
 * 好友
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-08-21
 */
@RestController
@RequestMapping("/common/friend")
@Api(tags="好友")
public class FriendController {
    @Autowired
    private FriendService friendService;

    /**
    *  获取所有好友
    * @param pageNum
    * @param pageSize
    * @param friend
    * @return
    */

    @GetMapping("/getFriendList")
    @ApiOperation("获取所有好友")
    public Result getFriendList(Friend friend, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value="pageSize",defaultValue = "100") Integer pageSize){
        return friendService.selectFriendList(friend,pageNum,pageSize);
    }


    @GetMapping("/getApplyFriendList")
    @ApiOperation("获取所有好友")
    public Result getApplyFriendList(Friend friend, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                @RequestParam(value="pageSize",defaultValue = "100") Integer pageSize){
        return friendService.selectApplyFriendList(friend,pageNum,pageSize);
    }



    @GetMapping("/getFriendInfo")
    @ApiOperation("根据id获取单个好友")
    public Result getFriendInfo(Integer id){
        return friendService.selectFriendInfo(id);
    }


    @GetMapping("/getFriendInfoById")
    @ApiOperation("根据id获取单个好友")
    public Result getFriendInfoById(Friend friend){
        return friendService.selectFriendInfoById(friend);
    }

    /**
    * 保存好友
    * @param friend
    * @return
    */
    @PostMapping("/saveFriendInfo")
    @ApiOperation("保存好友")
    public Result saveFriendInfo(@RequestBody Friend friend){
        return friendService.saveFriendInfo(friend);
    }


    /**
     * 更新好友
     * @param friend
     * @return
     */

    @PutMapping("/updateFriendInfo")
    @ApiOperation("更新好友")
    public Result updateFriendInfo(@RequestBody Friend friend){
        return friendService.updateFriendInfo(friend);
    }


    @PutMapping("/updateFriendInfo2")
    @ApiOperation("更新好友")
    public Result updateFriendInfo2(@RequestBody Friend friend){
        return friendService.updateFriendInfo2(friend);
    }
    /**
     * 根据id删除好友
     * @param id
     * @return
     */
    @DeleteMapping("/delFriendInfo")
    @ApiOperation("根据id删除好友")
    public Result delFriendInfo(Integer id){
        return friendService.delFriendInfo(id);
    }

    /**
     * 根据id集合批量删除好友
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchFriendInfo")
    @ApiOperation("根据id集合批量删除好友")
    public Result delBatchFriendInfo(String idList){
        return friendService.delBatchFriendInfo(idList);
    }


}