package com.soft.web.controller;

import com.soft.web.common.AjaxResult;
import com.soft.web.common.Ztree;
import com.soft.web.constant.UserConstants;
import com.soft.web.entity.SysMenu;
import com.soft.web.entity.SysRole;
import com.soft.web.entity.SysUser;
import com.soft.web.service.SysMenuService;
import com.soft.web.service.SysUserService;
import com.soft.web.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserService sysUserService;

    @RequiresPermissions("menu:view")
    @GetMapping("menu")
    public ModelAndView menu()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("menu/menu");
        return view;
    }

    @RequiresPermissions("menu:list")
    @PostMapping("/list")
    public List<SysMenu> list(SysMenu menu, HttpSession session)
    {
        SysUser user=null;
        try {
            user = (SysUser) session.getAttribute("user");
        } catch (Exception e) {  }
        List<SysMenu> menuList = sysMenuService.selectMenuList(menu, user);
        return menuList;
    }

    /**
     * 新增
     */
    @GetMapping("/add/{pid}")
    public ModelAndView add(@PathVariable("pid") Long pid)
    {
        SysMenu menu = null;
        if (0L != pid)
        {
            menu = sysMenuService.selectMenuById(pid);
        }
        else
        {
            menu = new SysMenu();
            menu.setId(0L);
            menu.setFullname("主目录");
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("menu/add");
        view.addObject("menu",menu);
        return view;
    }

    /**
     * 新增保存菜单
     */
    @RequiresPermissions("menu:add")
    @PostMapping("/add")
    public AjaxResult addSave(@Validated SysMenu menu)
    {
        if (UserConstants.MENU_NAME_NOT_UNIQUE.equals(sysMenuService.checkMenuNameUnique(menu)))
        {
            return AjaxResult.error("新增菜单'" + menu.getFullname() + "'失败，菜单名称已存在");
        }
        ShiroUtils.clearCachedAuthorizationInfo();
        return AjaxResult.toAjax(sysMenuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id)
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("menu/edit");
        view.addObject("menu",sysMenuService.selectMenuById(id));
        return view;
    }

    /**
     * 修改保存菜单
     */
    @RequiresPermissions("menu:edit")
    @PostMapping("/edit")
    public AjaxResult editSave(@Validated SysMenu menu)
    {
        if (UserConstants.MENU_NAME_NOT_UNIQUE.equals(sysMenuService.checkMenuNameUnique(menu)))
        {
            return AjaxResult.error("修改菜单'" + menu.getFullname() + "'失败，菜单名称已存在");
        }
        ShiroUtils.clearCachedAuthorizationInfo();
        return AjaxResult.toAjax(sysMenuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @RequiresPermissions("menu:remove")
    @GetMapping("/remove/{id}")
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        if (sysMenuService.selectCountMenuByParentId(id) > 0)
        {
            return AjaxResult.warn("存在子菜单,不允许删除");
        }
        if (sysMenuService.selectCountRoleMenuByMenuId(id) > 0)
        {
            return AjaxResult.warn("菜单已分配,不允许删除");
        }
        ShiroUtils.clearCachedAuthorizationInfo();
        return AjaxResult.toAjax(sysMenuService.deleteMenuById(id));
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/roleMenuTreeData")
    public Object roleMenuTreeData(SysRole role, HttpSession session){
        SysUser user = null;
        Long userid = null;
        try {
            user = (SysUser) session.getAttribute("user");
            userid= user.getId();
        } catch (Exception e) {  }
        List<Ztree> ztrees = sysMenuService.roleMenuTreeData(role, user.getId());
        return ztrees;
    }

    /**
     * 选择菜单图标
     */
    @GetMapping("/icon")
    public ModelAndView icon()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("menu/icon");
        return view;
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree/{id}")
    public ModelAndView selectMenuTree(@PathVariable("id") Long id)
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("menu/tree");
        view.addObject("menu",sysMenuService.selectMenuById(id));
        return view;
    }

    /**
     * 加载所有菜单列表树
     */
    @GetMapping("/menuTreeData")
    @ResponseBody
    public List<Ztree> menuTreeData()
    {
        SysUser user = sysUserService.getSessionUser();
        List<Ztree> ztrees = sysMenuService.menuTreeData(user.getId());
        return ztrees;
    }

    /**
     * 校验菜单名称
     */
    @PostMapping("/checkMenuNameUnique")
    public String checkMenuNameUnique(SysMenu menu)
    {
        return sysMenuService.checkMenuNameUnique(menu);
    }


}
