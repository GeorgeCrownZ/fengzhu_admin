package com.soft.web.controller;

import com.github.pagehelper.PageHelper;
import com.soft.web.common.AjaxResult;
import com.soft.web.common.MyListUtils;
import com.soft.web.common.PageDomain;
import com.soft.web.entity.Spec;
import com.soft.web.service.GoodsTagService;
import com.soft.web.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/goodstag")
public class GoodsTagController {

    @Autowired
    GoodsTagService goodsTagService;

    @PostMapping("/goodstagList")
    public Object list(PageDomain pageDomain) {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize());
        PageHelper.orderBy(pageDomain.getOrderBy());
        List<Spec> list = goodsTagService.selectSpecList();
        return MyListUtils.getDataTable(list);
    }

    @PostMapping("/removegoodstag")
    public AjaxResult removegoodstag(String ids) {
        try {
            return AjaxResult.toAjax(goodsTagService.deleteSpecByIds(ids));
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    @GetMapping("/addgoodstag")
    public ModelAndView addgoodstag() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("goods/addgoodstag");
        return modelAndView;
    }

    @PostMapping("/addgoodstag")
    public AjaxResult addsavegoodstag(Spec spec) {
        return AjaxResult.toAjax(goodsTagService.addSpec(spec));
    }

    @GetMapping("/editgoodstag/{id}")
    public ModelAndView editgoodstag(@PathVariable("id")Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Spec spec = goodsTagService.selectSpecById(id);
        modelAndView.addObject("spec",spec);
        modelAndView.setViewName("goods/editgoodstag");
        return modelAndView;
    }

    @PostMapping("/editgoodstag")
    public AjaxResult editupdategoodstag(Spec spec) {
        return AjaxResult.toAjax(goodsTagService.updateSpec(spec));
    }

    @PostMapping("/editstatus")
    public AjaxResult editstatus(Spec spec) {
        return AjaxResult.toAjax(goodsTagService.updateStatus(spec));
    }
}
