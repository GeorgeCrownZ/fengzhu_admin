package com.soft.web.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("fa_product_param")
public class ProductParam {
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;

    private Long productId;

    private String paramName;

    private String paramValue;

    @TableField(fill = FieldFill.INSERT)
    private Date addTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
