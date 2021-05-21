package com.soft.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("company")
public class CompanyController {
    @RequiresPermissions("company:view")
    @GetMapping("company")
    public ModelAndView company()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("company/company");
        return view;
    }
}
