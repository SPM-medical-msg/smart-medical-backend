package com.xxx.modules.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 诊断
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@Data
@TableName("`diagnose`")
@ApiModel(value="diagnose对象", description="诊断")
public class Diagnose {

    /**
     * 主键id
     */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键id")
	private Integer id;
    /**
     * 医生
     */
	@ApiModelProperty(value = "医生")
	private Integer doctorUserId;
    /**
     * 病人
     */
	@ApiModelProperty(value = "病人")
	private Integer userId;
    /**
     * 挂号编号
     */
	@ApiModelProperty(value = "挂号编号")
	private Integer orderId;
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
     * 诊断结果
     */
	@ApiModelProperty(value = "诊断结果")
	private String resultInfo;
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
	 * 医生
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "医生")
	private String doctorRealName;
	/**
	 * 病人
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "病人")
	private String realName;
	/**
	 * 编号
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "编号")
	private String orderNumber;
	/**
	 * 药品名
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "药品名")
	private String drugName;

}
