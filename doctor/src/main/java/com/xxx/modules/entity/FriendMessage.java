package com.xxx.modules.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 好友消息
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-08-22
 */
@Data
@TableName("`friend_message`")
@ApiModel(value="friend_message对象", description="好友消息")
public class FriendMessage {

    /**
     * 主键id
     */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键id")
	private Integer id;
    /**
     * 发送方用户id
     */
	@ApiModelProperty(value = "发送方用户id")
	private Integer sendUserId;
    /**
     * 接收方
     */
	@ApiModelProperty(value = "接收方")
	private Integer receiveUserId;
    /**
     * 内容
     */
    @Excel(name = "内容",width = 30)
	@ApiModelProperty(value = "内容")
	private String content;
    /**
     * 类型
     */
    @Excel(name = "类型", replace = {"文字_1","图片_2","文件_3","语音_4","视频_5",},width = 20)
	@ApiModelProperty(value = "类型")
	private Integer type;
    /**
     * 状态
     */
    @Excel(name = "状态", replace = {"未读_1","已读_2","撤回_3",},width = 20)
	@ApiModelProperty(value = "状态")
	private Integer status;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间",width = 30)
	@ApiModelProperty(value = "创建时间")
	private String createTime;
    /**
     * 更新时间
     */
    @Excel(name = "更新时间",width = 30)
	@ApiModelProperty(value = "更新时间")
	private String updateTime;
	/**
	 * 发送方
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "发送方")
	@Excel(name = "发送方",width=30)
	private String sendRealName;

	@TableField(exist = false)
	@ApiModelProperty(value = "发送方")
	@Excel(name = "发送方",width=30)
	private String imageUrl;
	/**
	 * 接收方
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "接收方")
	@Excel(name = "接收方",width=30)
	private String receiveRealName;


	private String fileUrl;

	@ApiModelProperty("是否已读,1-未读,2-已读")
	private Integer isView;

	private Integer strategyId;
}
