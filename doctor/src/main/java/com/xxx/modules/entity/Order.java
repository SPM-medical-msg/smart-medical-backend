package com.xxx.modules.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 挂号
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@Data
@TableName("`order`")
@ApiModel(value="order对象", description="挂号")
public class Order {

    /**
     * 主键id
     */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键id")
	private Integer id;
    /**
     * 挂号人
     */
	@ApiModelProperty(value = "挂号人")
	private Integer userId;
    /**
     * 医生
     */
	@ApiModelProperty(value = "医生")
	private Integer doctorUserId;
    /**
     * 编号
     */
    @Excel(name = "编号",width = 30)
	@ApiModelProperty(value = "编号")
	private String orderNumber;
    /**
     * 挂号日期
     */
    @Excel(name = "挂号日期",width = 30)
	@ApiModelProperty(value = "挂号日期")
	private String appointDay;
    /**
     * 周
     */
    @Excel(name = "周",width = 30)
	@ApiModelProperty(value = "周")
	private String weekDay;
    /**
     * 时间段
     */
    @Excel(name = "时间段", replace = {"08:00-10:00_1","10:00-12:00_2","14:00-16:00_3","16:00-18:00_4",},width = 20)
	@ApiModelProperty(value = "时间段")
	private Integer time;
    /**
     * 状态
     */
    @Excel(name = "状态", replace = {"待支付_1","支付完成_2",},width = 20)
	@ApiModelProperty(value = "状态")
	private Integer status;
    /**
     * 支付金额
     */
    @Excel(name = "支付金额",width = 30)
	@ApiModelProperty(value = "支付金额")
	private Double price;
    /**
     * 科室
     */
	@ApiModelProperty(value = "科室")
	private Integer deptId;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间",width = 30)
	@ApiModelProperty(value = "创建时间")
	private String createTime;
    /**
     * 更新时间
     */
    @Excel(name = "更新时间",width = 30)
	@ApiModelProperty(value = "更新时间")
	private String updateTime;
	/**
	 * 挂号人
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "挂号人")
	@Excel(name = "挂号人",width=30)
	private String realName;
	/**
	 * 医生
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "医生")
	@Excel(name = "医生",width=30)
	private String doctorRealName;
	/**
	 * 科室
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "科室")
	@Excel(name = "科室",width=30)
	private String deptName;

	@TableField(exist = false)
	private Integer planId;

}
