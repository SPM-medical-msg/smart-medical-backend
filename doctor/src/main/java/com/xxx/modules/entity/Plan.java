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
 * 排班
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-04
 */
@Data
@TableName("`plan`")
@ApiModel(value="plan对象", description="排班")
public class Plan {

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
	private Integer userId;
    /**
     * 日期
     */
    @Excel(name = "日期",width = 30)
	@ApiModelProperty(value = "日期")
	private String planDay;
    /**
     * 周
     */
    @Excel(name = "周",width = 30)
	@ApiModelProperty(value = "周")
	private String weekDay;
    /**
     * 类型
     */
    @Excel(name = "类型", replace = {"上午_1","下午_2",},width = 20)
	@ApiModelProperty(value = "类型")
	private Integer type;
    /**
     * 时间段
     */
    @Excel(name = "时间段", replace = {"08:00-10:00_1","10:00-12:00_2","14:00-16:00_3","16:00-18:00_4",},width = 20)
	@ApiModelProperty(value = "时间段")
	private Integer time;
    /**
     * 最大人数
     */
    @Excel(name = "最大人数",width = 30)
	@ApiModelProperty(value = "最大人数")
	private Integer count;
    /**
     * 挂号费用
     */
    @Excel(name = "挂号费用",width = 30)
	@ApiModelProperty(value = "挂号费用")
	private Double money;
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
	 * 医生
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "医生")
	@Excel(name = "医生",width=30)
	private String realName;

	@TableField(exist = false)
	private Integer subCount;
}
