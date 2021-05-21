package com.soft.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.soft.web.common.AjaxResult;
import com.soft.web.common.MyListUtils;
import com.soft.web.common.PageDomain;
import com.soft.web.entity.*;
import com.soft.web.service.OrderDetailService;
import com.soft.web.service.OrderLogService;
import com.soft.web.service.OrderService;
import com.soft.web.vo.OrderVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    OrderLogService orderLogService;

    @RequiresPermissions("order:view")
    @GetMapping("order")
    public ModelAndView order()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("order/order");
        return view;
    }

    @RequestMapping("list")
    public Object list(PageDomain pageDomain, OrderQueryParam param){
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderBy());
        List<OrderVO> list = orderService.orderlist(param);
        return MyListUtils.getDataTable(list);
    }

    @RequestMapping("orderdetail")
    public Object orderdetail(PageDomain pageDomain, String pid){
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderBy());
        List<OrderDetail> list = orderDetailService.orderDetailList(pid);
        return MyListUtils.getDataTable(list);
    }

    @RequiresPermissions("ordersend:view")
    @GetMapping("ordersend")
    public ModelAndView ordersend()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("order/ordersend");
        return view;
    }

    @RequiresPermissions("ordermail:view")
    @GetMapping("ordermail")
    public ModelAndView ordermail()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("order/ordermail");
        return view;
    }

    @GetMapping("express/{id}")
    public ModelAndView express(@PathVariable("id") Long id)
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("order/express");
        view.addObject("id",id);
        return view;
    }

    @PostMapping("expressave")
    public AjaxResult expressave(Long id, Long expressid, String expressNo)
    {
        AjaxResult ajaxResult = orderService.OrderSend(id,expressid,expressNo);
        return ajaxResult;
    }

    @GetMapping("rememory/{id}")
    public ModelAndView rememory(@PathVariable("id") Long id)
    {
        Order order = orderService.getById(id);
        ModelAndView view = new ModelAndView();
        view.setViewName("order/rememory");
        view.addObject("id", id);
        view.addObject("systemComments", order.getSystemComments());
        return view;
    }

    @PostMapping("rememorysave")
    public AjaxResult rememorysave(Long id, String systemComments)
    {
        AjaxResult ajaxResult = orderService.rememorySave(id, systemComments);
        return ajaxResult;
    }

    @GetMapping("address/{id}")
    public ModelAndView address(@PathVariable("id") Long id)
    {
        Order order = orderService.getById(id);
        ModelAndView view = new ModelAndView();
        view.setViewName("order/address");
        view.addObject("order", order);
        return view;
    }

    @PostMapping("addresssave")
    public AjaxResult addresssave(Long id, String receiveName,String receiveTelphone,String address)
    {
        AjaxResult ajaxResult = orderService.addressSave(id, receiveName,receiveTelphone,address);
        return ajaxResult;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Long id) throws ParseException {
        ModelAndView modelAndView = new ModelAndView();
        Order order = orderDetailService.selectOrderById(id);
        //  商品详情
        List<OrderDetail> orderDetails = orderDetailService.selectOrderDetailById(order.getId());
        //  log日志
        List<OrderLog> logList = orderLogService.selectLogsById(order.getOrderId());
        BigDecimal resultMoney = new BigDecimal(0);
        BigDecimal sums = new BigDecimal(0);
        for (OrderDetail orderDetail : orderDetails) {
            // 商品合计价格
            if(orderDetail.getSpecialofferprice()==null){
                orderDetail.setSpecialofferprice(new BigDecimal("0"));
            }
            if(order.getExpressFee()==null){
                order.setExpressFee(new BigDecimal("0"));
            }
            resultMoney = resultMoney.add(orderDetail.getSpecialofferprice());
            sums = sums.add(orderDetail.getProductPrice());
        }
        modelAndView.addObject("order", order);
        modelAndView.addObject("orderDetails", orderDetails);
        modelAndView.addObject("resultMoney", resultMoney);
        modelAndView.addObject("sums", sums);
        modelAndView.addObject("logList", logList);
        modelAndView.setViewName("order/detail");
        return modelAndView;
    }
}
