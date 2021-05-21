package com.soft.web.annotation;

//import com.soft.web.constant.OrderStatus;
import com.soft.web.constant.PayStatus;
import com.soft.web.constant.SendStatus;
import com.soft.web.constant.TradeStatus;
import com.soft.web.entity.Order;
import com.soft.web.entity.OrderLog;
import com.soft.web.entity.SysUser;
import com.soft.web.service.OrderLogService;
import com.soft.web.service.OrderService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

@Aspect
@Component
public class OrderLogAspect {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderLogService orderLogService;

    @Autowired
    private HttpSession session;

    @Pointcut("@annotation(com.soft.web.annotation.OrderLogAOP)")
    public void fun(){

    }


    @Before("fun()")
    public void before(JoinPoint joinPoint){
//        MethodSignature methodSignature=(MethodSignature)joinPoint.getSignature();
//        Method method=methodSignature.getMethod();
//        OrderLogAOP orderLogAOP = method.getAnnotation(orderLogAOP.class);
//        System.out.println("orderLog before");

    }

    @After(("fun()"))
    public void after(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Method method=((MethodSignature)joinPoint.getSignature()).getMethod();
        OrderLogAOP orderLogAOP = method.getAnnotation(OrderLogAOP.class);
        Long oid = Long.parseLong(args[0].toString());
        Order order = orderService.getById(oid);
        Integer tradeStatus = order.getTradeStatus();
        Integer payStatus = order.getPayStatus();
        SysUser user = (SysUser) session.getAttribute("user");
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderId(order.getOrderId());
        orderLog.setUserId(user.getId());
        orderLog.setOpName(user.getUsername());
        orderLog.setRemark(orderLogAOP.memo());
        orderLog.setTradeStatus(TradeStatus.toString(tradeStatus));
        orderLog.setPayStatus(PayStatus.toString(payStatus));
        int r = orderLogService.insert(orderLog);
        System.out.println("orderLog after:"+orderLogAOP.memo());

    }

}
