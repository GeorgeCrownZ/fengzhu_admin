package com.soft.web.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.soft.web.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.PrimitiveIterator;

@Data
@TableName("fa_log")
public class Log {
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;

    private Long userId;

    @Excel(name = "操作者")
    private String opname;

    @Excel(name = "模块")
    private String modelname;

    @Excel(name = "方法")
    private String methodname;

    @Excel(name = "备注")
    private String remark;

    @Excel(name = "ip地址")
    private String ip;
    private Integer duration;

    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "时间",dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date addTime;
}
