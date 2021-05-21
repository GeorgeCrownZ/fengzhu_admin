package com.soft.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("randpic")
public class RandpicController {
    @RequiresPermissions("randpic:view")
    @GetMapping("randpic")
    public ModelAndView randpic()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("randpic/randpic");
        return view;
    }
}
