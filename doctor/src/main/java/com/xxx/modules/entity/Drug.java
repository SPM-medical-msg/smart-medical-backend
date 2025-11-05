package com.xxx.modules.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 药品
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-05-11
 */
@Data
@TableName("`drug`")
@ApiModel(value="drug对象", description="药品")
public class Drug {

    /**
     * 主键id
     */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键id")
	private Integer id;
    /**
     * 药品名
     */
	@ApiModelProperty(value = "药品名")
	private String drugName;
    /**
     * 数量
     */
	@ApiModelProperty(value = "数量")
	private Integer count;
    /**
     * 简介
     */
	@TableField(value = "`desc`")
	@ApiModelProperty(value = "简介")
	private String desc;
    /**
     * 详情
     */
	@ApiModelProperty(value = "详情")
	private String content;
    /**
     * 分类
     */
	@ApiModelProperty(value = "分类")
	private Integer sortId;
    /**
     * 状态
     */
	@ApiModelProperty(value = "状态")
	private Integer status;
    /**
     * 图片
     */
	@ApiModelProperty(value = "图片")
	private String imageUrl;
    /**
     * 价格
     */
	@ApiModelProperty(value = "价格")
	private Double price;
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
	 * 分类
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "分类")
	private String sortName;

}
