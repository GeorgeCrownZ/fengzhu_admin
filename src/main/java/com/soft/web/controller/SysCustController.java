package com.soft.web.controller;

import com.github.pagehelper.PageHelper;
import com.soft.web.common.AjaxResult;
import com.soft.web.common.MyListUtils;
import com.soft.web.common.PageDomain;
import com.soft.web.common.SessionUtil;
import com.soft.web.entity.SysCust;
import com.soft.web.entity.SysUser;
import com.soft.web.service.SysCustService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("cust")
public class SysCustController {
    @Autowired
    private SysCustService sysCustService;

    @RequiresPermissions("cust:view")
    @GetMapping("cust")
    public ModelAndView cust()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("cust/cust");
        return view;
    }

    @RequiresPermissions("cust:list")
    @PostMapping("/list")
    public Object list(PageDomain pageDomain, SysCust cust)
    {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize());
        PageHelper.orderBy(pageDomain.getOrderBy());
        List<SysCust> list = sysCustService.selectCustList(cust);
        return MyListUtils.getDataTable(list);
    }

    /**
     * 新增客户
     */
    @GetMapping("/add")
    public ModelAndView add()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("cust/add");
        return view;
    }

    /**
     * 新增保存客户
     */
    @RequiresPermissions("cust:add")
    @PostMapping("/add")
    public AjaxResult addSave(SysCust cust, HttpSession session)
    {
        SysUser user = SessionUtil.getSessionUser(session,"user");
        String uname = (user!=null)?user.getUsername():"";
        cust.setCreateby(uname);
        return AjaxResult.toAjax(sysCustService.insertCust(cust));
    }

    /**
     * 修改客户
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long cid)
    {
        ModelAndView view = new ModelAndView();
        SysCust cust = sysCustService.selectCustById(cid);
        view.addObject("cust",cust);
        view.setViewName("cust/edit");
        return view;
    }

    /**
     * 修改保存客户
     */
    @RequiresPermissions("system:cust:edit")
    @PostMapping("/edit")
    public AjaxResult editSave(SysCust cust,HttpSession session)
    {
        SysUser user = SessionUtil.getSessionUser(session,"user");
        String uname = (user!=null)?user.getUsername():"";
        cust.setUpdateBy(uname);
        return AjaxResult.toAjax(sysCustService.updateCust(cust));
    }

    @RequiresPermissions("cust:remove")
    @PostMapping("/remove")
    public AjaxResult remove(String ids)
    {
        try
        {
            return AjaxResult.toAjax(sysCustService.deleteCustByIds(ids));
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

}
