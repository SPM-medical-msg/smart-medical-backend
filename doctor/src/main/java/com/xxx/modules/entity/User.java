package com.xxx.modules.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@Data
@TableName("`user`")
@ApiModel(value="user对象", description="用户")
public class User {

    /**
     * 主键id
     */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键id")
	private Integer id;
    /**
     * 用户名
     */
	@ApiModelProperty(value = "用户名")
	private String userName;
    /**
     * 密码
     */
	@ApiModelProperty(value = "密码")
	private String password;
    /**
     * 姓名
     */
	@ApiModelProperty(value = "姓名")
	private String realName;
    /**
     * 联系方式
     */
	@ApiModelProperty(value = "联系方式")
	private String phone;
    /**
     * 性别
     */
	@ApiModelProperty(value = "性别")
	private Integer sex;
    /**
     * 科室
     */
	@ApiModelProperty(value = "科室")
	private Integer deptId;
    /**
     * 专业方向
     */
	@ApiModelProperty(value = "专业方向")
	private String majorInfo;
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
     * 评级
     */
//	@ApiModelProperty(value = "评级")
//	private String score;
    /**
     * 用户类型
     */
	@ApiModelProperty(value = "用户类型")
	private Integer userType;
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
	/**
	 * 科室名
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "科室名")
	private String deptName;

	/**
	 * 验证码code值
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "验证码code值")
	private String code;

	private String workTime;

	private Double money;

	@TableField(exist = false)
	private Double score;

	private String address;
}
