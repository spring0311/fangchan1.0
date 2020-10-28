package com.example.fangchan.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    @ApiModelProperty(value = "图片")
    @TableField("PICTURE")
    private String picture;

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

    @ApiModelProperty(value = "已砍价人数")
    @TableField("HELP_NUM")
    private Integer helpNum;

    @ApiModelProperty(value = "一阶段金额")
    @TableField("TOP_ONE")
    private Integer topOne;

    @ApiModelProperty(value = "二阶段金额")
    @TableField("TOP_TWO")
    private Integer topTwo;

    @ApiModelProperty(value = "三阶段金额")
    @TableField("TOP_THREE")
    private Integer topThree;

    @ApiModelProperty(value = "已完成金额")
    @TableField("TOP_MUCH")
    private Integer topMuch;


    @ApiModelProperty(value = "完成度百分比")
    @TableField("PERCENTAGE")
    private Double percentage;

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("OPEN_TIME")
    private Date openTime;

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("END_TIME")
    private Date endTime;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "MODIFY_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;

    /**
     * 映射 list
     * <p>
     * 头像 金额 时间
     */
    @TableField(exist = false)
    private List<TOrderWechat> OWList;

    /**
     * 判断是否砍过
     * 0否 1是
     */
    @TableField(exist = false)
    private Integer isHelp;

    /**
     * 用于特例查询
     */
    @TableField(exist = false)
    private Long wechatIdForSelect;

    /**
     * 点亮金额
     */
    @TableField(exist = false)
    private Integer money;

}
