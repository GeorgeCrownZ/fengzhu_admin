package com.soft.web.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("fa_user")
public class Member {
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;
    private Long pid;
    private String userAccount;
    private String headPic;
    private String userNick;
    private String trueName;
    private String mobileNumber;
    private String password;
    private String passwordSalt;
    private String sex;
    private String pincode;
    private Integer userState;
    private Integer isEmployee;
    private Long shopId;
    private String shopName;
    private String payPassword;
    private String openId;
    private Integer grade1;
    private Integer grade2;
    private BigDecimal giveScore;
    private BigDecimal serviceScore;
    private BigDecimal consumeScore;
    private BigDecimal cashoutScore;
    private String comeFrom;
    private String qrCode;
    private BigDecimal selfScore;
    private BigDecimal totalScore;

    @TableField(fill = FieldFill.INSERT)
    private Date addTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private transient Integer ordercount;

    private transient Member member;
}
