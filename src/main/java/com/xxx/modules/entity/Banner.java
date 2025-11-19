package com.xxx.modules.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 轮播图
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@Data
@TableName("`banner`")
@ApiModel(value="banner对象", description="轮播图")
public class Banner {

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
     * 图片
     */
	@ApiModelProperty(value = "图片")
	private String imageUrl;
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

}
