package com.xxx.modules.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 评论
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-04-05
 */
@Data
@TableName("`evaluate`")
@ApiModel(value="evaluate对象", description="评论")
public class Evaluate {

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
     * 医生
     */
	@ApiModelProperty(value = "医生")
	private Integer doctorUserId;
    /**
     * 评分
     */
	@ApiModelProperty(value = "评分")
	private Double score;
    /**
     * 评论
     */
	@ApiModelProperty(value = "评论")
	private String comment;
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
	/**
	 * 姓名
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "姓名")
	private String realName;
	/**
	 * 头像
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "头像")
	private String imageUrl;
	/**
	 * 医生
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "医生")
	private String doctorRealName;

}
