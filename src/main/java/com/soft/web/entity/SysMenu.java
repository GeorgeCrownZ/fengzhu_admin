package com.soft.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName("sys_menu")
public class SysMenu {
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;
    private Long pid;
    private String perms;
    private String fullname;
    private String mtype;
    private String icon;
    private String urladdress;
    private String target;
    private Integer sortcode;
    private Boolean state;
    private String remark;
    private Date addtime;
    private Date updatetime;

    private transient List<SysMenu> sonlist;
    private transient String parentname;

}
