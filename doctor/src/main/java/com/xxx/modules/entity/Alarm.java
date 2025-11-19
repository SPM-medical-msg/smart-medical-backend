package com.xxx.modules.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 报警
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
@Data
@TableName("`alarm`")
@ApiModel(value="alarm对象", description="报警")
public class Alarm {

    /**
     * 主键id
     */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键id")
	private Integer id;
    /**
     * 预警药品
     */
	@ApiModelProperty(value = "预警药品")
	private Integer drugId;
    /**
     * 预警信息
     */
	@ApiModelProperty(value = "预警信息")
	private String messageInfo;
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
	 * 预警药品
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "预警药品")
	private String drugName;

}
