package com.soft.web.service;

import com.soft.web.dao.MemberOrdersMapper;
import com.soft.web.entity.Order;
import com.soft.web.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberOrderService {

    @Autowired
    MemberOrdersMapper memberOrdersMapper;

    public List<Order> selectOrdersByUserid(Long userId) {
        return memberOrdersMapper.selectOrdersByUserid(userId);
    }

    public List<OrderDetail> selectByOrderId(String orderId) {
        return memberOrdersMapper.selectByOrderId(orderId);
    }
}
