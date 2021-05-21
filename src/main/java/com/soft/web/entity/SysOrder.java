package com.soft.web.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_order")
public class SysOrder implements Serializable {
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;
    private Long userid;
    private Long goodsid;
    private Integer num;
    private Date updatetime;
    private Date addtime;

    public SysOrder(){

    }

    public SysOrder(Long id, Long userid, Long goodsid, Integer num, Date updatetime, Date addtime) {
        this.id = id;
        this.userid = userid;
        this.goodsid = goodsid;
        this.num = num;
        this.updatetime = updatetime;
        this.addtime = addtime;
    }


}
