package com.soft.web.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("fa_product_say")
public class GoodsSay {
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;
    private Long pid;
    private Long productId;
    private String productName;
    private String productPic;
    private Long userId;
    private String userName;
    private String userPhone;
    private Integer sayLevel;
    private String sayContent;
    private Integer beautifulStatus;
    private Integer hideStatus;
    @TableField(fill = FieldFill.INSERT)
    private Date addTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
