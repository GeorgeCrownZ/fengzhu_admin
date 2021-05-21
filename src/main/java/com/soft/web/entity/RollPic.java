package com.soft.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("fa_rollpic")
public class RollPic implements Serializable {

    private Long id;
    private String picPath;
    private Integer sortNumber;
    private Integer status;
    private Date addTime;
    private Date updateTime;
}
