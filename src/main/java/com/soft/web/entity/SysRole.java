package com.soft.web.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_role")
public class SysRole {
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;

    private String rolename;

    private String rolekey;

    private String remark;

    private Integer sortcode;

//    @TableField(exist = false)
    private transient boolean flag = false;

//    @TableField(exist = false)
    private transient Long[] menuIds;

}
