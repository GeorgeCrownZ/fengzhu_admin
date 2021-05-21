package com.soft.web.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.soft.web.annotation.Excel;
import lombok.Data;

@Data
@TableName("sys_user")
public class SysUser {
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;

    @Excel(name = "用户名称")
    private String username;

    private String password;

    @Excel(name = "真实姓名")
    private String realname;

    private Long roleid;

    private Integer sortcode;

    @Excel(name = "邮件")
    private String email;

    @Excel(name = "电话")
    private String phone;

    private String avatar;

//    @TableField(exist = false)
    private transient String rolename;

}
