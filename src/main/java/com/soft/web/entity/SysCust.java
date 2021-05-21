package com.soft.web.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_cust")
public class SysCust implements Serializable{
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;

    private String custname;

    private String custphone;

    private Boolean status;

    private String createby;

    @TableField(value = "createtime",fill = FieldFill.INSERT)
    private Date createtime;

    private String updateBy;

    @TableField(value = "updatetime",fill = FieldFill.INSERT_UPDATE)
    private Date updatetime;

    private String remark;

    public String toString(){
        String json = JSONObject.toJSONString(this);
        return json;
    }


}

