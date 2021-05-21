package com.soft.web.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("fa_productinfo")
public class Product {
    @TableId(value = "product_id", type= IdType.AUTO)
    private Long productId;
    private String productName;
    private Integer productCategoryId;
    private Integer channelId;
    private BigDecimal price;
    private Integer currentInventory;
    private String measurement;
    private String lable;
    private Integer indexno;
    private String specifications1;
    private String specifications2;
    private String specifications3;
    private String imgurl1;
    private String imgurl2;
    private String imgurl3;
    private String imgurl4;
    private String imgurl5;
    private String imgurl6;
    private String imgurl7;
    private String imgurl8;
    private Integer specialoffer;
    private String recommend;
    private BigDecimal specialofferprice;
    private String storage_conditions;
    private String shelf_life;
    private String net_content;
    private String activity_url;
    private Integer updownStatus;
    private Integer salenum;
    private Integer salenum2;
    private Integer showIndex;

    private Date updownTime;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private transient String specid;
}
