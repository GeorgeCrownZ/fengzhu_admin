package com.soft.web.service;

import com.soft.web.common.AjaxResult;
import com.soft.web.dao.OrderDetailMapper;
import com.soft.web.entity.Order;
import com.soft.web.entity.OrderDetail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderDetailService  {
    @Resource
    private OrderDetailMapper orderDetailMapper;

    public List<OrderDetail> orderDetailList(String pid) {
        return orderDetailMapper.orderDetailList(pid);
    }

    public Order selectOrderById(Long id) {
        return orderDetailMapper.selectOrderById(id);
    }

    public List<OrderDetail> selectOrderDetailById(Long oid) {
        return orderDetailMapper.selectOrderDetailById(oid);
    }

    public AjaxResult updateOrderStatus(Long id) {
        Order order = selectOrderById(id);
        if(order.getOrderStatus()!=null && order.getOrderStatus()!=1) {
            return AjaxResult.error("该订单已失效");
        }
        if(order.getTradeStatus()!=null && order.getTradeStatus()==1) {
            return AjaxResult.error("该订单已经取消");
        }
        if(order.getTradeStatus()!=null && order.getTradeStatus()==3) {
            return AjaxResult.error("该订单已发货");
        }
        if(order.getTradeStatus()!=null && order.getTradeStatus()==4) {
            return AjaxResult.error("该订单已被签收");
        }
        if(order.getPayStatus()!=null && order.getPayStatus()==0) {
            return AjaxResult.error("该订单未付款");
        }
        int result = orderDetailMapper.updateOrderStatus(id);
        if(result>0) {
            return AjaxResult.success("取消订单成功");
        } else {
            return AjaxResult.error("取消订单失败");
        }

    }
}
