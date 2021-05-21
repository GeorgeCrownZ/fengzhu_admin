package com.soft.web.controller;

import com.github.pagehelper.PageHelper;
import com.soft.web.common.AjaxResult;
import com.soft.web.common.MyListUtils;
import com.soft.web.common.PageDomain;
import com.soft.web.entity.CallUs;
import com.soft.web.entity.GoodsSay;
import com.soft.web.service.CallUsService;
import com.soft.web.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/callus")
public class CallUsController {

    @Autowired
    CallUsService callUsService;

    @GetMapping("/callusList")
    public ModelAndView callusList() {
        return new ModelAndView("callus/calluslist");
    }

    @PostMapping("/alllist")
    public Object alllist(PageDomain pageDomain,String searchkey) {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderBy());
        List<CallUs> callUses = callUsService.selectAllList(searchkey);
        TableDataInfo calls = MyListUtils.getDataTable(callUses);
        return calls;
    }

    @PostMapping("/backsay")
    public Object backsay(Long pid) {
        List<CallUs> callUses = callUsService.selectAllListByPid(pid);
        return MyListUtils.getDataTable(callUses);
    }

    @GetMapping("/replysay/{id}")
    public ModelAndView replysay(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("callus/replysay");
        modelAndView.addObject("id",id);
        return modelAndView;
    }

    @PostMapping("/replysaysave")
    public AjaxResult replysaysave(CallUs callUs) {
        int result = callUsService.insertCall(callUs);
        if(result>0) {
            return AjaxResult.success("回复成功");
        }
        return AjaxResult.error("已经回复了");
    }
}
