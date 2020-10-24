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
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 砍价表
 * </p>
 *
 * @author weizihao
 * @since 2020-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TOrder对象", description = "")
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ORDER_ID", type = IdType.AUTO)
    private Long orderId;

    @ApiModelProperty(value = "房产名称")
    @TableField("ORDER_NAME")
    private String orderName;

    @ApiModelProperty(value = "微信登录用户ID")
    @TableField("WECHAT_ID")
    private Long wechatId;

    @ApiModelProperty(value = "微信用户姓名")
    @TableField("WECHAT_NAME")
    private String wechatName;

    @ApiModelProperty(value = "微信用户手机号")
    @TableField("WECHAT_PHONE")
    private String wechatPhone;

    @ApiModelProperty(value = "后台创建用户ID")
    @TableField("USER_ID")
    private Long userId;

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
    private Double topOne;

    @ApiModelProperty(value = "二阶段金额")
    @TableField("TOP_TWO")
    private Double topTwo;

    @ApiModelProperty(value = "三阶段金额")
    @TableField("TOP_THREE")
    private Double topThree;

    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("OPEN_TIME")
    private Date openTime;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("END_TIME")
    private Date endTime;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "MODIFY_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;


}
