package com.soft.web.controller;

import com.soft.web.common.MyListUtils;
import com.soft.web.entity.Order;
import com.soft.web.entity.OrderDetail;
import com.soft.web.service.MemberOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/memberorder")
public class MemberOrderController {

    @Autowired
    MemberOrderService memberOrderService;

    @PostMapping("/userorders/{id}")
    public Object userorders(@PathVariable("id")Long id) {
        List<Order> orders = memberOrderService.selectOrdersByUserid(id);
        return MyListUtils.getDataTable(orders);
    }

    @PostMapping("/orderdetails/{id}")
    public Object orderdetails(@PathVariable("id")String id) {
        List<OrderDetail> orderDetails = memberOrderService.selectByOrderId(id);
        return MyListUtils.getDataTable(orderDetails);
    }
}
