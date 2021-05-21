package com.soft.web.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("fa_spec")
public class Spec {
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;

    private String specName;

    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private Date addTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    //是否选中
    private transient Boolean ck;

    private transient Integer count;

}
