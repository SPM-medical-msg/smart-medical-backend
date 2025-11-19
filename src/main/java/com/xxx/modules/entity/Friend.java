package com.xxx.modules.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 好友
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-08-21
 */
@Data
@TableName("`friend`")
@ApiModel(value="friend对象", description="好友")
public class Friend {

    /**
     * 主键id
     */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键id")
	private Integer id;
    /**
     * 用户
     */
	@ApiModelProperty(value = "用户")
	private Integer userId;
    /**
     * 好友id
     */
	@ApiModelProperty(value = "好友id")
	private Integer friendId;
    /**
     * 好友名
     */
	@ApiModelProperty(value = "好友名")
	@TableField(exist = false)
	private String realName;
    /**
     * 好友头像
     */
	@ApiModelProperty(value = "好友头像")
	@TableField(exist = false)
	private String imageUrl;
    /**
     * 创建时间
     */
	@ApiModelProperty(value = "创建时间")
	private String createTime;
    /**
     * 更新时间
     */
	@ApiModelProperty(value = "更新时间")
	private String updateTime;

	@ApiModelProperty(value = "最新消息")
	private String newMessage;

	@TableField(exist = false)
	private Integer type = 1; //单聊--固定

	@TableField(exist = false)
	private Integer count;

	@ApiModelProperty("1-申请中,2-已通过，3-已拒绝，4-已忽略")
	private Integer status;

	private String applyMessage;

	@TableField(exist = false)
	private Integer sex;

	@TableField(exist = false)
	private String userName;

	@ApiModelProperty(value = "主动加好友的人")
	private Integer activeAddUserId;

	@ApiModelProperty(value = "接受加好友的人")
	private Integer acceptAddUserId;

	private Integer friendType;

	@TableField(exist = false)
	private Integer updateId;
}
