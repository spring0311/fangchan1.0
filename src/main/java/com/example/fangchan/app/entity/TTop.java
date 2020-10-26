package com.example.fangchan.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 三阶段对象
 * </p>
 *
 * @author weizihao
 * @since 2020-10-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TTop对象", description = "")
public class TTop implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "一阶段人数")
    @TableField("TOP_ONE_NUM")
    private Integer topOneNum;

    @ApiModelProperty(value = "二阶段人数")
    @TableField("TOP_TWO_NUM")
    private Integer topTwoNum;

    @ApiModelProperty(value = "三阶段人数")
    @TableField("TOP_THREE_NUM")
    private Integer topThreeNum;

    @ApiModelProperty(value = "一阶段金额")
    @TableField("TOP_ONE")
    private Integer topOne;

    @ApiModelProperty(value = "二阶段金额")
    @TableField("TOP_TWO")
    private Integer topTwo;

    @ApiModelProperty(value = "三阶段金额")
    @TableField("TOP_THREE")
    private Integer topThree;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "MODIFY_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;


}
