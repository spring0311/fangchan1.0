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
 * 订单用户映射表
 * </p>
 *
 * @author weizihao
 * @since 2020-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TOrderWechat对象", description = "")
public class TOrderWechat implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ORDER_WECHAT_ID", type = IdType.AUTO)
    private Long orderWechatId;

    /**
     * 订单ID
     */
    @TableField("ORDER_ID")
    private Long orderId;

    /**
     * 用户ID
     */
    @TableField("WECHAT_ID")
    private Long wechatId;

    /**
     * 头像
     */
    @TableField("AVA")
    private String ava;

    /**
     * 昵称
     */
    @TableField("NICK_NAME")
    private String nickName;

    /**
     * 砍价金额
     */
    @TableField("MONEY")
    private Double money;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "MODIFY_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;


}