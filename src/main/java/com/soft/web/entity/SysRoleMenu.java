package com.soft.web.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_rolemenu")
public class SysRoleMenu {
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;
    private Long roleid;
    private Long menuid;

}
