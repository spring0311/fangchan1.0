package com.example.fangchan.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 积分记录
 * </p>
 *
 * @author weizihao
 * @since 2020-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TScoreRecord对象", description = "")
public class TScoreRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "记录ID")
    @TableId(value = "SCORE_RECORD_ID", type = IdType.AUTO)
    private Long scoreRecordId;

    @ApiModelProperty(value = "主表ID")
    @TableField("SCORE_ID")
    private Long scoreId;

    @ApiModelProperty(value = "加减 0加1减")
    @TableField("UP_DOWN")
    private Integer upDown;

    @ApiModelProperty(value = "记录名称 用户名或商品名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "加减值")
    @TableField("NUM")
    private Integer num;

    @ApiModelProperty(value = "图片")
    @TableField("PICTURE")
    private String picture;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    @TableField(value = "MODIFY_TIME", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date modifyTime;


}
