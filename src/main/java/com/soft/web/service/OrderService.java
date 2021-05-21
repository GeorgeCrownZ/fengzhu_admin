package com.soft.web.service;

import com.soft.web.annotation.OrderLogAOP;
import com.soft.web.common.AjaxResult;
import com.soft.web.constant.TradeStatus;
import com.soft.web.constant.PayStatus;
import com.soft.web.constant.PayWay;
import com.soft.web.dao.ExpressMapper;
import com.soft.web.dao.OrderMapper;
import com.soft.web.entity.Express;
import com.soft.web.entity.Member;
import com.soft.web.entity.Order;
import com.soft.web.entity.OrderQueryParam;
import com.soft.web.vo.KeyValueVO;
import com.soft.web.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service("order")
public class OrderService{
    @Resource
    private OrderMapper orderMapper;
    @Autowired
    ExpressMapper expressMapper;

    public List<OrderVO> orderlist(OrderQueryParam param) {
        return orderMapper.orderlist(param);
    }

    public Order getById(Long id) {
        return orderMapper.selectById(id);
    }

    @OrderLogAOP(memo="商家发货")
    public AjaxResult OrderSend(Long id, Long expressid, String expressNo) {
        try {
            System.out.println(id);
            System.out.println(expressNo);
            System.out.println(expressid);
            Order order = orderMapper.selectById(id);
            if(order.getPayStatus() != PayStatus.Paid)
            {
                return AjaxResult.error("订单未付款");
            }
            if(order.getTradeStatus() != TradeStatus.Sending)
            {
                return AjaxResult.error("订单不是待发货状态");
            }

            Express express = expressMapper.selectById(expressid);
            order.setTradeStatus(TradeStatus.Receiving);
            order.setExpressNo(expressNo);
            order.setExpressName(express.getExpressName());
            order.setExpressId(express.getId());
            Integer r = orderMapper.updateById(order);
            if(r>0){
                return AjaxResult.success("操作成功");
            }
            else{
                return AjaxResult.error("操作失败");
            }
        }
        catch (Exception ex){
            return AjaxResult.error("操作失败");
        }

    }

    @OrderLogAOP(memo="修改了备注")
    public AjaxResult rememorySave(Long id, String systemComments) {

        Order order = orderMapper.selectById(id);
        if(order.getTradeStatus() != TradeStatus.Sending)
        {
            return AjaxResult.error("订单不是待发货状态");
        }
        order.setSystemComments(systemComments);
        Integer r = orderMapper.updateById(order);
        if(r>0){
            return AjaxResult.success("操作成功");
        }
        else{
            return AjaxResult.error("操作失败");
        }
    }

    @OrderLogAOP(memo="修改了收货地址")
    public AjaxResult addressSave(Long id, String receiveName, String receiveTelphone, String address) {

        Order order = orderMapper.selectById(id);
        if(order.getTradeStatus() != TradeStatus.Sending)
        {
            return AjaxResult.error("订单不是待发货状态");
        }
        order.setReceiveName(receiveName);
        order.setReceiveTelphone(receiveTelphone);
        order.setAddress(address);
        Integer r = orderMapper.updateById(order);
        if(r>0){
            return AjaxResult.success("操作成功");
        }
        else{
            return AjaxResult.error("操作失败");
        }
    }

    public List<KeyValueVO> getPayStatusList(){
        return PayStatus.getList(true);
    }

    public List<KeyValueVO> getTradeStatusList(){
        return TradeStatus.getList(true);
    }
}
