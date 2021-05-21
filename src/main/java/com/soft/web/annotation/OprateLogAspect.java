package com.soft.web.annotation;

import com.soft.web.constant.PayStatus;
import com.soft.web.constant.SendStatus;
import com.soft.web.constant.TradeStatus;
import com.soft.web.entity.Log;
import com.soft.web.entity.Order;
import com.soft.web.entity.OrderLog;
import com.soft.web.entity.SysUser;
import com.soft.web.service.LogService;
import com.soft.web.util.CommonUtil;
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
public class OprateLogAspect {
    @Autowired
    private HttpSession session;

    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.soft.web.annotation.OprateLogAOP)")
    public void fun(){

    }

    @Before("fun()")
    public void before(JoinPoint joinPoint){

    }

    @After(("fun()"))
    public void after(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Method method=((MethodSignature)joinPoint.getSignature()).getMethod();
        OprateLogAOP oprateLogAOP = method.getAnnotation(OprateLogAOP.class);
        SysUser user = (SysUser) session.getAttribute("user");
        AnnotationResolver annotationResolver = AnnotationResolver.newInstance();
        Object remark = annotationResolver.resolver(joinPoint, oprateLogAOP.remark());
        Log log = new Log();
        log.setUserId(user.getId());
        log.setOpname(user.getUsername());
        log.setModelname(oprateLogAOP.model());
        log.setMethodname(oprateLogAOP.method());
        log.setRemark(remark.toString());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = null;
        try {
            ip = CommonUtil.toIpAddr(request);
        } catch (Exception e) {
            ip = "无法获取登录用户Ip";
        }
        log.setIp(ip);
        int r = logService.insert(log);
        System.out.println("oprateLog"+remark.toString());
    }

}
