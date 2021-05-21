package com.soft.web.controller;

import com.github.pagehelper.PageHelper;
import com.soft.web.common.AjaxResult;
import com.soft.web.common.MyListUtils;
import com.soft.web.common.PageDomain;
import com.soft.web.entity.ProductParam;
import com.soft.web.service.ProductParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class GoodsParamController {

    @Autowired
    private ProductParamService productParamService;

    @RequestMapping("goods/goodsparam")
    public ModelAndView goodsparam(Long pid)
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("goods/goodsparam");
        view.addObject("pid", pid);
        return view;
    }

    @PostMapping("goods/paramlist")
    public Object paramlist(PageDomain pageDomain,Long pid)
    {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderBy());
        List<ProductParam> list = productParamService.productParamList(pid);
        return MyListUtils.getDataTable(list);
    }

    @PostMapping("goods/addparam")
    public AjaxResult addparam(ProductParam productParam)
    {
        return AjaxResult.toAjax(productParamService.addparam(productParam));
    }

    @PostMapping("goods/paramremove")
    public AjaxResult paramremove(String ids)
    {
        return AjaxResult.toAjax(productParamService.delparam(ids));
    }
}
