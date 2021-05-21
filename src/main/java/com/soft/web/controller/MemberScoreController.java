package com.soft.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MemberScoreController {

    @RequiresPermissions("memberscore:view")
    @GetMapping("member/memberscore")
    public ModelAndView memberscore()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/memberscore");
        return view;
    }
}
