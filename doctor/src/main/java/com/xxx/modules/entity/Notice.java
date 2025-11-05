package com.xxx.modules.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 公告
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-01-20
 */
@Data
@TableName("`notice`")
@ApiModel(value="notice对象", description="公告")
public class Notice {

    /**
     * 主键id
     */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键id")
	private Integer id;
    /**
     * 标题
     */
	@ApiModelProperty(value = "标题")
	private String title;
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
     * 图片
     */
	@ApiModelProperty(value = "图片")
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

}
