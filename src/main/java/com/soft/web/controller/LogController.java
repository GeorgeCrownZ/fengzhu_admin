package com.soft.web.controller;

import com.github.pagehelper.PageHelper;
import com.soft.web.common.MyListUtils;
import com.soft.web.common.PageDomain;
import com.soft.web.entity.Log;
import com.soft.web.entity.Member;
import com.soft.web.entity.SysUser;
import com.soft.web.service.LogService;
import com.soft.web.util.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("log")
public class LogController {
    @Autowired
    private LogService logService;

    @RequiresPermissions("log:view")
    @GetMapping("log")
    public ModelAndView log()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("log/log");
        return view;
    }

    @PostMapping("list")
    public Object list(PageDomain pageDomain, String searchkey)
    {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderBy());
        List<Log> list = logService.loglist(searchkey);
        return MyListUtils.getDataTable(list);
    }

    @PostMapping("export")
    public Object export(String searchkey)
    {
        List<Log> list = logService.loglist(searchkey);
        ExcelUtil<Log> util = new ExcelUtil<Log>(Log.class);
        return util.exportExcel(list, "log");
    }
}
