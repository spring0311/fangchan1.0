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
 * 
 * </p>
 *
 * @author weizihao
 * @since 2020-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TPicture对象", description="")
public class TPicture implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "轮播图ID")
      @TableId(value = "PICTURE_ID", type = IdType.AUTO)
    private Long pictureId;

    @ApiModelProperty(value = "轮播图排序")
    @TableField("NUMBER")
    private Integer number;

    @ApiModelProperty(value = "图片地址")
    @TableField("URL")
    private String url;

      @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

      @TableField(value = "MODIFY_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;


}
