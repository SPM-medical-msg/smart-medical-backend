package com.xxx.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@TableName("`notice`")
@ApiModel(value="notice对象", description="公告")
public class Notice {

	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键id")
	private Integer id;

	@ApiModelProperty(value = "标题")
	private String title;

	@ApiModelProperty(value = "科室id")
	private Integer deptId;

	@TableField(value = "`desc`")
	@ApiModelProperty(value = "简介")
	private String desc;

	@ApiModelProperty(value = "详情")
	private String content;

	@ApiModelProperty(value = "图片")
	private String imageUrl;

	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@ApiModelProperty(value = "更新时间")
	private String updateTime;

	// ========== 新增字段 ==========
	/**
	 * 科室名称 - 关联查询使用，数据库表中不存在
	 */
	@TableField(exist = false)  // 重要：表示数据库中没有这个字段
	@ApiModelProperty(value = "科室名称", hidden = true)
	private String deptName;
}