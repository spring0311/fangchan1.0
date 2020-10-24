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
 *商品
 * </p>
 *
 * @author weizihao
 * @since 2020-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TComm对象", description = "")
public class TComm implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "COMMODITY_ID", type = IdType.AUTO)
    private Long commodityId;

    @ApiModelProperty(value = "图片")
    @TableField("PICTURE")
    private String picture;

    @ApiModelProperty(value = "积分价格")
    @TableField("MONEY")
    private Integer money;

    @ApiModelProperty(value = "商品名称")
    @TableField("NAME")
    private String name;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @TableField(value = "MODIFY_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;


}
