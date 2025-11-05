package com.xxx.modules.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 采购
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
@Data
@TableName("`purchase`")
@ApiModel(value="purchase对象", description="采购")
public class Purchase {

    /**
     * 主键id
     */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键id")
	private Integer id;
    /**
     * 采购编号
     */
	@ApiModelProperty(value = "采购编号")
	private String orderNumber;
    /**
     * 标题
     */
	@ApiModelProperty(value = "标题")
	private String title;
    /**
     * 采购药品
     */
	@ApiModelProperty(value = "采购药品")
	private Integer drugId;
    /**
     * 数量
     */
	@ApiModelProperty(value = "数量")
	private Integer count;
    /**
     * 状态
     */
	@ApiModelProperty(value = "状态")
	private Integer status;
    /**
     * 申请人
     */
	@ApiModelProperty(value = "申请人")
	private Integer userId;
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
	 * 药品名
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "药品名")
	private String drugName;
	/**
	 * 申请人
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "申请人")
	private String realName;

}
