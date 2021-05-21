package com.soft.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("callme")
public class CallMeController {
    @RequiresPermissions("callme:view")
    @GetMapping("callme")
    public ModelAndView callme()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("callme/callme");
        return view;
    }


}
