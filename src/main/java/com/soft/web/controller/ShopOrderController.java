package com.soft.web.controller;

import com.github.pagehelper.PageHelper;
import com.soft.web.common.AjaxResult;
import com.soft.web.common.MyListUtils;
import com.soft.web.common.PageDomain;
import com.soft.web.entity.Order;
import com.soft.web.entity.Shop;
import com.soft.web.entity.ShopOrderQueryParam;
import com.soft.web.service.ShopOrderService;
import com.soft.web.util.ExcelUtil;
import com.soft.web.vo.ShopOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/shoporder")
public class ShopOrderController {

    @Autowired
    ShopOrderService shopOrderService;

    @GetMapping("/shoporder")
    public ModelAndView shoporder() {
        ModelAndView modelAndView = new ModelAndView();
        List<Shop> shops = shopOrderService.selectAllShops();
        modelAndView.addObject("shops", shops);
        modelAndView.setViewName("shop/shoporder");
        return modelAndView;
    }

    @PostMapping("/orderlist")
    public Object orderlist(PageDomain pageDomain, ShopOrderQueryParam shopOrderQueryParam) {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderBy());
        List<ShopOrderVO> orders = shopOrderService.selectOrders(shopOrderQueryParam);
        return MyListUtils.getDataTable(orders);
    }

    @PostMapping("/export")
    public AjaxResult export(ShopOrderQueryParam shopOrderQueryParam) {
        List<ShopOrderVO> shopOrderVOS = shopOrderService.selectOrders(shopOrderQueryParam);
        ExcelUtil<ShopOrderVO> util = new ExcelUtil<ShopOrderVO>(ShopOrderVO.class);
        return util.exportExcel(shopOrderVOS, "门店订单");
    }
}
