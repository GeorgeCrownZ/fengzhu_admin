package com.soft.web.vo;

import com.soft.web.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("ShopOrderVO")
public class ShopOrderVO implements Serializable {

    private Long id;
    @Excel(name = "门店名称")
    private String shopName;
    @Excel(name = "订单编号")
    private String orderId;
    @Excel(name = "订单金额")
    private BigDecimal payAmount;
    @Excel(name = "用户账号")
    private String userAccount;
    @Excel(name = "支付方式", readConverterExp = "1=微信,2=支付宝")
    private Integer payWay;
    @Excel(name = "付款时间", dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date payTime;

}
