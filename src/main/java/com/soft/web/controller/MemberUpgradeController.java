package com.soft.web.controller;

import com.github.pagehelper.PageHelper;
import com.soft.web.common.MyListUtils;
import com.soft.web.common.PageDomain;
import com.soft.web.entity.UserGroupScore;
import com.soft.web.entity.UserUpgrade;
import com.soft.web.entity.UserUpgradeQueryParam;
import com.soft.web.service.UserUpgradeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class MemberUpgradeController {

    @Autowired
    UserUpgradeService userUpgradeService;

    @RequiresPermissions("memberupgrade:view")
    @GetMapping("member/memberupgrade")
    public ModelAndView memberupgrade() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/memberupgrade");
        return view;
    }

    @PostMapping("/memberupgrade/upgradelist")
    public Object upgradelist(PageDomain pageDomain, UserUpgradeQueryParam queryParam) {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderBy());
        List<UserUpgrade> userUpgrades = userUpgradeService.selectUpgradList(queryParam);
        return MyListUtils.getDataTable(userUpgrades);
    }

    /*@RequiresPermissions("memberscore:view")
    @GetMapping("member/memberscore")
    public ModelAndView memberscore() {
        return new ModelAndView("member/memberscore");
    }*/

    @PostMapping("memberupgrade/scorelist")
    public Object scorelist(PageDomain pageDomain, UserUpgradeQueryParam queryParam) {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderBy());
        List<UserGroupScore> userGroupScores = userUpgradeService.selectUpgradeScoreList(queryParam);
        return MyListUtils.getDataTable(userGroupScores);
    }
}
