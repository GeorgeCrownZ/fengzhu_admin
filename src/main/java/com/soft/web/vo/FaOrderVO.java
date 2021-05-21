package com.soft.web.vo;

import com.soft.web.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaOrderVO {

    //  订单id
    private String orderId;
    //  用户id
    private String userId;
    //  用户账号
    private String userAccount;
    //  支付类型：1 微信支付 2 支付宝支付
    private Integer payWay;
    //  物流id
    private Long expressId;
    //  物流（快递公司名）
    private String expressName;

    //  收货人姓名
    private String receiveName;
    //  手机号码
    private String receiveTelPhone;
    //  收货地址
    private String address;

    //  商品
    private OrderDetail orderDetail;

    //  实收金额
    private Double specialOfferPrice;

    //  商品合计
    private Double productAmount;
    //  运费
    private Double expressFee;
    //  订单总金额

    //  应付款金额
    private Double payAmount;
}
