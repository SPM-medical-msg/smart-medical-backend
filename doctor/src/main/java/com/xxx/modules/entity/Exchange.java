package com.xxx.modules.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 药品订单
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
@Data
@TableName("`exchange`")
@ApiModel(value="exchange对象", description="药品订单")
public class Exchange {

    /**
     * 主键id
     */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键id")
	private Integer id;
    /**
     * 订单编号
     */
	@ApiModelProperty(value = "订单编号")
	private String orderNumber;
    /**
     * 药品
     */
	@ApiModelProperty(value = "药品")
	private Integer drugId;
    /**
     * 数量
     */
	@ApiModelProperty(value = "数量")
	private Integer count;
    /**
     * 单价
     */
	@ApiModelProperty(value = "单价")
	private Double price;
    /**
     * 总价
     */
	@ApiModelProperty(value = "总价")
	private Double totalPrice;
    /**
     * 用户
     */
	@ApiModelProperty(value = "用户")
	private Integer userId;
    /**
     * 联系方式
     */
	@ApiModelProperty(value = "联系方式")
	private String phone;
    /**
     * 地址
     */
	@ApiModelProperty(value = "地址")
	private String address;
    /**
     * 状态
     */
	@ApiModelProperty(value = "状态")
	private Integer status;
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
	 * 药品
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "药品")
	private String drugName;
	/**
	 * 姓名
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "姓名")
	private String realName;

}
