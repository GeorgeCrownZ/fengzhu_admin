package com.soft.web.service;

import com.soft.web.dao.OrderLogMapper;
import com.soft.web.entity.OrderLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class OrderLogService {

    @Resource
    private OrderLogMapper orderLogMapper;

    public int insert(OrderLog orderLog) {
        return orderLogMapper.insert(orderLog);
    }

    public List<OrderLog> selectLogsById(String orderId){
        List<OrderLog> logList = orderLogMapper.selectLogsById(orderId);
        return logList;
    }
}
