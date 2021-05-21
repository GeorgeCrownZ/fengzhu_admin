package com.soft.web.controller;

import com.github.pagehelper.PageHelper;
import com.soft.web.common.AjaxResult;
import com.soft.web.common.MyListUtils;
import com.soft.web.common.PageDomain;
import com.soft.web.constant.UserConstants;
import com.soft.web.entity.SysRole;
import com.soft.web.service.SysRoleService;
import com.soft.web.util.ShiroUtils;
import com.soft.web.util.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @RequiresPermissions("role:view")
    @GetMapping("role")
    public ModelAndView role()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("role/role");
        return view;
    }

    @RequiresPermissions("role:list")
    @PostMapping("/list")
    public Object list(PageDomain pageDomain, SysRole role)
    {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderBy());
        List<SysRole> list = sysRoleService.selectRoleList(role);
        return MyListUtils.getDataTable(list);
    }

    @GetMapping("/add")
    public ModelAndView add()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("role/add");
        view.addObject("roles", sysRoleService.selectRoleAll());
        return view;
    }

    /**
     * 新增保存角色
     */
    @RequiresPermissions("role:add")
    @PostMapping("/add")
    public AjaxResult addSave(@Validated SysRole role)
    {
        if (UserConstants.ROLE_NAME_NOT_UNIQUE.equals(sysRoleService.checkRoleNameUnique(role)))
        {
            return AjaxResult.error("新增角色'" + role.getRolename() + "'失败，角色名称已存在");
        }
        else if (UserConstants.ROLE_KEY_NOT_UNIQUE.equals(sysRoleService.checkRoleKeyUnique(role)))
        {
            return AjaxResult.error("新增角色'" + role.getRolename() + "'失败，角色权限已存在");
        }
        ShiroUtils.clearCachedAuthorizationInfo();
        return AjaxResult.toAjax(sysRoleService.insertRole(role));
    }

    /**
     * 修改角色
     */
    @GetMapping("/edit/{roleId}")
    public ModelAndView edit(@PathVariable("roleId") Long roleId)
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("role/edit");
        view.addObject("role", sysRoleService.getbyid(roleId));
        return view;
    }

    /**
     * 修改保存角色
     */
    @RequiresPermissions("role:edit")
    @PostMapping("/edit")
    public AjaxResult editSave(@Validated SysRole role)
    {
        if (UserConstants.ROLE_NAME_NOT_UNIQUE.equals(sysRoleService.checkRoleNameUnique(role)))
        {
            return AjaxResult.error("修改角色'" + role.getRolename() + "'失败，角色名称已存在");
        }
        else if (UserConstants.ROLE_KEY_NOT_UNIQUE.equals(sysRoleService.checkRoleKeyUnique(role)))
        {
            return AjaxResult.error("修改角色'" + role.getRolename() + "'失败，角色权限已存在");
        }
        ShiroUtils.clearCachedAuthorizationInfo();
        return AjaxResult.toAjax(sysRoleService.updateRole(role));
    }

    @RequiresPermissions("role:remove")
    @PostMapping("/remove")
    public AjaxResult remove(String ids)
    {
        try
        {
            return AjaxResult.toAjax(sysRoleService.deleteRoleByIds(ids));
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 校验角色名称
     */
    @PostMapping("/checkRoleNameUnique")
    public String checkRoleNameUnique(SysRole role)
    {
        return sysRoleService.checkRoleNameUnique(role);
    }

    /**
     * 校验角色权限
     */
    @PostMapping("/checkRoleKeyUnique")
    public String checkRoleKeyUnique(SysRole role)
    {
        return sysRoleService.checkRoleKeyUnique(role);
    }
}
