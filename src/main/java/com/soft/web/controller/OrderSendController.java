package com.soft.web.controller;

import com.soft.web.common.AjaxResult;
import com.soft.web.entity.OrderQueryParam;
import com.soft.web.service.OrderSendService;
import com.soft.web.util.ExcelUtil;
import com.soft.web.vo.OrderSendVO;
import com.soft.web.vo.OrderVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ordersend")
public class OrderSendController {

    @Autowired
    OrderSendService orderSendService;

    @RequiresPermissions("ordersend:export")
    @PostMapping("/export")
    public AjaxResult export(OrderQueryParam param) throws IOException, ClassNotFoundException {
        List<OrderSendVO> list = orderSendService.selectOrderVOS(param);
        ExcelUtil<OrderSendVO> util = new ExcelUtil<OrderSendVO>(OrderSendVO.class);
        return util.exportExcel(list, "订单数据");
    }

    @RequiresPermissions("ordersend:import")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<OrderSendVO> util = new ExcelUtil<OrderSendVO>(OrderSendVO.class);
        List<OrderSendVO> voList = util.importExcel(file.getInputStream());
        int result = orderSendService.updateByOrderId(voList);
        if(result>0) {
            return AjaxResult.success("导入成功");
        }
        return AjaxResult.error("导入失败");
    }

    @RequiresPermissions("ordersend:view")
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<OrderSendVO> util = new ExcelUtil<OrderSendVO>(OrderSendVO.class);
        return util.importTemplateExcel("订单模板");
    }
}
