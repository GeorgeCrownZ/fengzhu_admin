package com.soft.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("fa_product_category")
public class ProductCategory {
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;

    private Long pid;

    private String categoryName;

    private String urlimg;

    private Integer sortnum;

    private Integer status;

    private transient Integer productcount;

}
