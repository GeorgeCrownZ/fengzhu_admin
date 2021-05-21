package com.soft.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("fa_global")
public class Global {

    @TableId(value = "id", type= IdType.INPUT)
    private Long id;
    private String webName;
    private String webLogo;
    private String webRecord;
    private Double deliveryCost;
    private String noticePic1;
    private String noticePic2;
    private String noticePic3;
    private String noticePic4;
    private Integer noticeSwitch;
    private String userAgreement;
    private Double cashOutDown;
    private Integer cashOutNum;
    private Double cashOutFee;
    private Integer cashOutSwitch;
    private String distParam;

    private Long upgradeDirector1;
    private Integer upgradeDirector2;
    private Integer upgradeManager1;
    private Integer upgradeManager2;
    private Integer upgradeChiefinspector1;
    private Integer upgradeChiefinspector2;
    private Integer upgradeCeo1;
    private Integer upgradeCeo2;
    private Double share1;
    private Double share2;
    private Double directorDifferenceAward;
    private Double managerDifferenceAward;
    private Double chiefinspectorDifferenceAward;
    private Double ceoDifferenceAward;
    private Double managementAward;
    private Double integral;
    private Double purchaseCap;

    private String wechatAppid;
    private String wechatMerchantNum;
    private String wechatToken;
    private String wechatCallbackUrl;
    private String wechatCertPem;
    private String wechatKeyPem;

    // 短信签名
    private String smsSignature;
    // 身份验证验证码
    private String authenticationCode;
    // 登录确认验证码
    private String loginCode;
    // 登录异常验证码
    private String loginExceptionCode;
    // 用户注册验证码
    private String registerCode;
    // 修改密码验证码
    private String updatePasswordCode;
    // 信息变更验证码
    private String informationChangeCode;
    // AccessKeyID
    private String accessKeyId;
    // AccessKeySecret
    private String accessKeySecret;
    // 快递授权key
    private String authenticationKey;
    // customer
    private String customer;

    //  发件人
    private String fromName;
    //  发货人电话
    private String fromTelminePic;
    //  发货人地址
    private String fromAddress;

    private String minePic1;

    private String minePic2;
}
