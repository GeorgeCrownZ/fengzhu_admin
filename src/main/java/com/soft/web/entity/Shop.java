package com.soft.web.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@TableName("fa_shop")
@Alias("Shop")
public class Shop {
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;
    private String shopName;
    private String shopPic;
    private Integer sort;
    private Integer status;
    @TableField(fill = FieldFill.INSERT)
    private Date addTime;


}
