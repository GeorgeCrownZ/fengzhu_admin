package com.soft.web.controller;


import com.github.pagehelper.PageHelper;
import com.soft.web.common.AjaxResult;
import com.soft.web.common.MyListUtils;
import com.soft.web.common.PageDomain;
import com.soft.web.entity.Express;
import com.soft.web.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author md
 * @since 2021-03-22
 */
@RestController
@RequestMapping("/express")
public class ExpressController {

    @Autowired
    ExpressService expressService;

    @GetMapping("/ordermail")
    public ModelAndView ordermail() {
        return new ModelAndView("order/ordermail");
    }

    @PostMapping("/ordermail")
    public Object mail(PageDomain pageDomain, String searchkey) {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize());
        PageHelper.orderBy(pageDomain.getOrderBy());
        return MyListUtils.getDataTable(expressService.selectAllExpress(searchkey));
    }

    @GetMapping("/addexpress")
    public ModelAndView toaddexpress() {
        return new ModelAndView("order/addexpress");
    }

    @PostMapping("/addexpress")
    public AjaxResult addexpress(Express express) {
        int result = expressService.addExpress(express);
        if(result>0) {
            return AjaxResult.toAjax(result);
        }
        return AjaxResult.error("添加失败");
    }

    @GetMapping("/updateexpress/{id}")
    public ModelAndView toupdateexpress(@PathVariable("id") Long id) {
        Express express = expressService.selectExpressById(id);
        ModelAndView modelAndView = new ModelAndView("order/updateexpress");
        modelAndView.addObject("express",express);
        return modelAndView;
    }

    @PostMapping("/updateexpress")
    public AjaxResult updateexpress(Express express) {
        int result = expressService.updateExpress(express);
        if(result>0) {
            return AjaxResult.toAjax(result);
        }
        return AjaxResult.error("修改失败");
    }

    @PostMapping("/deleteexpress")
    public AjaxResult deleteexpress(String ids) {
        int result = expressService.deleteByIds(ids);
        if(result>0) {
            return AjaxResult.toAjax(result);
        }
        return AjaxResult.error("删除失败");
    }
}

