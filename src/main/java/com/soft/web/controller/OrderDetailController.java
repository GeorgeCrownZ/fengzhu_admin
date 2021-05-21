package com.soft.web.controller;

import com.soft.web.common.AjaxResult;
import com.soft.web.common.MyListUtils;
import com.soft.web.service.OrderDetailService;
import com.soft.web.service.OrderLogService;
import com.soft.web.vo.FaOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("orderdetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/cancelorder/{id}")
    public AjaxResult cancelorder(@PathVariable("id")Long id) {
        return orderDetailService.updateOrderStatus(id);
    }

}

