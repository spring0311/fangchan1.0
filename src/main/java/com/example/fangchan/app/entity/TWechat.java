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
 * 微信用户
 * </p>
 *
 * @author weizihao
 * @since 2020-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TWechat对象", description = "")
public class TWechat implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "微信用户ID")
    @TableId(value = "WECHAT_ID", type = IdType.AUTO)
    private Long wechatId;

    @ApiModelProperty(value = "昵称")
    @TableField("NICK_NAME")
    private String nickName;

    @ApiModelProperty(value = "openId")
    @TableField("OPEN_ID")
    private String openId;

    @ApiModelProperty(value = "手机号")
    @TableField("PHONE")
    private String phone;

    @ApiModelProperty(value = "头像地址")
    @TableField("AVA")
    private String ava;

    @ApiModelProperty(value = "姓名")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "砍价订单ID")
    @TableField("ORDER_ID")
    private Long orderId;

    @ApiModelProperty(value = "积分ID")
    @TableField("SCORE_ID")
    private Long scoreId;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    @TableField(value = "MODIFY_TIME", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date modifyTime;


}
