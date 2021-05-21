package com.soft.web.vo;

import com.soft.web.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderVO {

    private Long id;

    private String orderId;
    // 真实姓名
    private String trueName;
    // 商品ID
    private Long productId;
    // 商品图片
    private String productIcon;
    // 商品名称
    private String productName;
    // 商品数量
    private Integer productQuantity;
    // 商品价格
    private BigDecimal productPrice;
    // 优惠价
    private BigDecimal specialofferprice;
    // 支付状态：1未付款，0已付款
    private int payStatus;
    // 支付类型：1微信，2支付宝
    private int payWay;
    // 用户id
    private Long userId;
    // 订单状态：1已取消，2待发货，3待收货，4已签收
    private int tradeStatus;
    // 订单时间
    private Date createTime;



}
