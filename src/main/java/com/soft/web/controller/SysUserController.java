package com.soft.web.controller;

import com.github.pagehelper.PageHelper;
import com.soft.web.annotation.OprateLogAOP;
import com.soft.web.common.AjaxResult;
import com.soft.web.common.MyListUtils;
import com.soft.web.common.PageDomain;
import com.soft.web.constant.UserConstants;
import com.soft.web.entity.SysUser;
import com.soft.web.service.SysRoleService;
import com.soft.web.service.SysUserService;
import com.soft.web.util.ExcelUtil;
import com.soft.web.util.ShiroUtils;
import com.soft.web.util.TableDataInfo;
import com.soft.web.vo.OrderSendVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @RequiresPermissions("user:view")
    @GetMapping("user")
    public ModelAndView user()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("user/user");
        return view;
    }

    @RequiresPermissions("user:list")
    @PostMapping("/list")
    public Object list(PageDomain pageDomain, SysUser user)
    {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize());
        PageHelper.orderBy(pageDomain.getOrderBy());
        List<SysUser> list = sysUserService.selectUserList(user);
        return MyListUtils.getDataTable(list);
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public ModelAndView add()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("user/add");
        view.addObject("roles", sysRoleService.selectRoleAll());
        return view;
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("user:add")
    @PostMapping("/add")
    public AjaxResult addSave(SysUser user)
    {
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(sysUserService.checkLoginNameUnique(user.getUsername())))
        {
            return AjaxResult.error("新增用户'" + user.getUsername() + "'失败，登录账号已存在");
        }
        else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(sysUserService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUsername() + "'失败，手机号码已存在");
        }
        else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(sysUserService.checkEmailUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUsername() + "'失败，邮箱账号已存在");
        }
        String md5pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5pwd);
        return AjaxResult.toAjax(sysUserService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userid}")
    public ModelAndView edit(@PathVariable("userid") Long userId)
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("user/edit");
        view.addObject("user", sysUserService.selectUserById(userId));
        view.addObject("roles", sysRoleService.selectRolesByUserId(userId));
        return view;
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("user:edit")
    @PostMapping("/edit")
    public AjaxResult editSave(SysUser user)
    {
        if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(sysUserService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUsername() + "'失败，手机号码已存在");
        }
        else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(sysUserService.checkEmailUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUsername() + "'失败，邮箱账号已存在");
        }
        return AjaxResult.toAjax(sysUserService.updateUser(user));
    }

    @RequiresPermissions("user:remove")
    @PostMapping("/remove")
    public AjaxResult remove(String ids)
    {
        try
        {
            return AjaxResult.toAjax(sysUserService.deleteUserByIds(ids));
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    public String checkLoginNameUnique(SysUser user)
    {
        return sysUserService.checkLoginNameUnique(user.getUsername());
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    public String checkEmailUnique(SysUser user)
    {
        return sysUserService.checkEmailUnique(user);
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    public String checkPhoneUnique(SysUser user)
    {
        return sysUserService.checkPhoneUnique(user);
    }

    @PostMapping("/export")
    public AjaxResult export(SysUser user)
    {
        List<SysUser> list = sysUserService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.exportExcel(list, "sysUser");
    }

    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport,HttpSession session) throws Exception
    {
        SysUser user=null;
        try {
            user = (SysUser) session.getAttribute("user");
            ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
            List<SysUser> userList = util.importExcel(file.getInputStream());
            String operName = user.getUsername();
            String message = sysUserService.importUser(userList, updateSupport, operName);
            return AjaxResult.success(message);
        } catch (Exception e) {
            return AjaxResult.error("操作错误");
        }

    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.importTemplateExcel("管理员模板");
    }
}
