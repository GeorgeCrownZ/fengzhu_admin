package com.soft.web.service;

import com.soft.web.annotation.OprateLogAOP;
import com.soft.web.dao.OrderSendMapper;
import com.soft.web.entity.*;
import com.soft.web.vo.OrderSendVO;
import com.soft.web.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderSendService {

    @Autowired
    OrderSendMapper orderSendMapper;

    public Order selectByOrderId(String orderId) {
        return orderSendMapper.selectByOrderId(orderId);
    }

    @Transactional
    @OprateLogAOP(model = "发货导入管理",method = "导入")
    public int updateByOrderId(List<OrderSendVO> voList) {
        int result = 0;
        try{
            for (OrderSendVO orderSendVO : voList) {
                Order orderResult = selectByOrderId(orderSendVO.getOrderId());
                if(orderResult!=null) {
                    if(orderResult.getTradeStatus()!=null && orderResult.getPayStatus()!=null && orderResult.getOrderStatus()!=null) {
                        if((orderResult.getTradeStatus()==1 || orderResult.getTradeStatus()==2) && orderResult.getPayStatus()==1 && orderResult.getOrderStatus()==1)  {
                            Express express = orderSendMapper.selectExpress(orderSendVO.getExpressName());
                            orderSendVO.setExpressId(express.getId());
                            orderSendMapper.updateStatus(orderSendVO);
                            result++;
                        }
                    }

                }
            }
            if(result>0) {
                return 1;
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @OprateLogAOP(model = "发货导入管理",method = "导出")
    public List<OrderSendVO> selectOrderVOS(OrderQueryParam param) {
        List<OrderSendVO> orderVOS = orderSendMapper.selectOrderVOS(param);
        for (OrderSendVO orderVO : orderVOS) {
            List<OrderDetail> products = orderVO.getProducts();
            StringBuffer productResult = new StringBuffer();
            for (OrderDetail product : products) {
                productResult.append(product.getProductName()+ "(" +product.getProductQuantity()+ "),");
            }
            orderVO.setProductAbouts(productResult.toString());
            Global global = orderSendMapper.selectGlobal();
            orderVO.setGlobal(global);
        }
        return orderVOS;
    }

    public int updateStatus(OrderSendVO orderVO) {
        Express express = selectExpress(orderVO.getExpressName());
        orderVO.setExpressId(express.getId());
        int result = orderSendMapper.updateStatus(orderVO);
        if(result>0) {
            return result;
        }
        return -1;
    }

    public Express selectExpress(String expressName) {
        return orderSendMapper.selectExpress(expressName);
    }
}
