package com.soft.web.vo;

import com.soft.web.annotation.Excel;
import com.soft.web.annotation.Excels;
import com.soft.web.entity.Global;
import com.soft.web.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSendVO implements Serializable {

    @Excel(name = "订单时间", dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    @Excel(name = "订单编号")
    private String orderId;
    @Excel(name = "收件人姓名")
    private String receiveName;
    @Excel(name = "收件人电话")
    private String receiveTelPhone;
    @Excel(name = "收件地址")
    private String address;

    /*@Excels({
            @Excel(name = "发货信息", targetAttr = "OrderDetail.productName"),
            @Excel(name = "数量", targetAttr = "OrderDetail.productQuantity")
    })*/
    private List<OrderDetail> products;

    @Excel(name = "发货信息")
    private transient String productAbouts;
    @Excels({
            @Excel(name = "发件人", targetAttr = "fromName"),
            @Excel(name = "发件人电话", targetAttr = "fromTel"),
            @Excel(name = "发件人地址", targetAttr = "fromAddress")
    })
    private Global global;
    @Excel(name = "备注")
    private String systemComments;
    @Excel(name = "代收金额")
    private transient String collectionAmount;
    @Excel(name = "保价金额")
    private transient String insuredValue;
    @Excel(name = "业务类型")
    private transient String businessType;
    @Excel(name = "快递名称")
    private String expressName;
    @Excel(name = "快递单号")
    private String expressNo;

    private Long expressId;
}
