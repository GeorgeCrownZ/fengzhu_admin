package com.soft.web.controller;

import com.github.pagehelper.PageHelper;
import com.soft.web.common.AjaxResult;
import com.soft.web.common.MyListUtils;
import com.soft.web.common.PageDomain;
import com.soft.web.entity.RollPic;
import com.soft.web.service.RollPicService;
import com.soft.web.util.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rollpic")
public class RollPicController {

    @Autowired
    RollPicService rollPicService;

    @GetMapping("/rollList")
    public ModelAndView torollpic()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("rollpic/rollList");
        return view;
    }

    @PostMapping("/rollList")
    public Object rollList(PageDomain pageDomain) {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize());
        PageHelper.orderBy(pageDomain.getOrderBy());
        List<RollPic> rollPics = rollPicService.selectAllRollPic();
        return MyListUtils.getDataTable(rollPics);
    }

    @GetMapping("/toaddroll")
    public ModelAndView toaddroll() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("rollpic/addroll");
        return modelAndView;
    }

    @PostMapping("/addroll")
    public AjaxResult addroll(RollPic rollPic, @RequestParam("pic") MultipartFile pic) {
        if(!pic.isEmpty()) {
            try {
                String webLogoPath = FileUploadUtils.upload(com.soft.web.util.Global.getRollPic(), pic);
                rollPic.setPicPath(webLogoPath);
                int result = rollPicService.addRollPic(rollPic);
                if(result>0) {
                    return AjaxResult.toAjax(result);
                }
            } catch (IOException e) {
                return AjaxResult.toAjax(-1);
            }
        }
        return AjaxResult.toAjax(-1);
    }

    @GetMapping("/toupdateroll/{id}")
    public ModelAndView toeditroll(@PathVariable("id") Long id) {
        RollPic rollPic = rollPicService.selectRollPicById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roll",rollPic);
        modelAndView.setViewName("rollpic/updateroll");
        return modelAndView;
    }

    @PostMapping("/updateroll")
    public AjaxResult updateroll(RollPic rollPic, @RequestParam("pic") MultipartFile pic) {
        if(!pic.isEmpty()) {
            try {
                String webLogoPath = FileUploadUtils.upload(com.soft.web.util.Global.getRollPic(), pic);
                rollPic.setPicPath(webLogoPath);
                rollPic.setUpdateTime(new Date());
            } catch (IOException e) {
                return AjaxResult.toAjax(-1);
            }
        }
        int result = rollPicService.updateRollPicById(rollPic);
        if(result>0) {
            return AjaxResult.toAjax(result);
        }
        return AjaxResult.toAjax(-1);
    }

    @PostMapping("/deleterolls")
    public AjaxResult deleterolls(String ids) {
        try {
            return AjaxResult.toAjax(rollPicService.deleteRollPics(ids));
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
    }

    @PostMapping("/rollupdown")
    public AjaxResult rollupdown(RollPic rollPic) {
        return AjaxResult.toAjax(rollPicService.updateStatus(rollPic));
    }
}
