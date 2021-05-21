package com.soft.web.controller;

import com.soft.web.common.AjaxResult;
import com.soft.web.entity.SysUser;
import com.soft.web.service.SysUserService;
import com.soft.web.util.FileUploadUtils;
import com.soft.web.util.Global;
import com.soft.web.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 个人信息 业务处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/user/profile")
public class SysProfileController {

    @Autowired
    private SysUserService sysUserService;
    /**
     * 个人信息
     */
    @GetMapping()
    public ModelAndView profile()
    {
        SysUser user = sysUserService.getSessionUser();
        ModelAndView view = new ModelAndView();
        view.setViewName("user/profile");
        view.addObject("user",user);
        view.addObject("roleGroup",sysUserService.selectUserRoleGroup(user.getId()));
        return view;
    }

    /**
     * 修改头像
     */
    @GetMapping("/avatar")
    public ModelAndView ModelAndView()
    {
        SysUser user = sysUserService.getSessionUser();
        ModelAndView view = new ModelAndView();
        view.setViewName("user/avatar");
        view.addObject("user",sysUserService.selectUserById(user.getId()));
        return view;
    }

    /**
     * 保存头像
     */
    @PostMapping("/updateAvatar")
    public AjaxResult updateAvatar(@RequestParam("avatarfile") MultipartFile file)
    {
        SysUser currentUser = sysUserService.getSessionUser();
        try
        {
            if (!file.isEmpty())
            {
                String avatar = FileUploadUtils.upload(Global.getAvatarPath(), file);
                currentUser.setAvatar(avatar);
                if (sysUserService.updateUserInfo(currentUser) > 0)
                {
                    return AjaxResult.success();
                }
            }
            return AjaxResult.error();
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 修改用户
     */
    @PostMapping("/update")
    public AjaxResult update(SysUser user)
    {
        SysUser currentUser = sysUserService.getSessionUser();
        currentUser.setRealname(user.getRealname());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhone(user.getPhone());
        if (sysUserService.updateUserInfo(currentUser) > 0)
        {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    @GetMapping("/checkPassword")
    public boolean checkPassword(String password)
    {
        SysUser user = sysUserService.getSessionUser();
        if (sysUserService.matchesPassword(user, password))
        {
            return true;
        }
        return false;
    }

    @PostMapping("/resetPwd")
    public AjaxResult resetPwd(String oldPassword, String newPassword)
    {
        SysUser user =  sysUserService.getSessionUser();
        if (StringUtils.isNotEmpty(newPassword) && sysUserService.matchesPassword(user, oldPassword))
        {
            if (sysUserService.resetUserPwd(user,newPassword) > 0)
            {
                return AjaxResult.success();
            }
            return AjaxResult.error();
        }
        else
        {
            return AjaxResult.error("修改密码失败，旧密码错误");
        }
    }

    @GetMapping("/resetPwd")
    public ModelAndView resetPwd()
    {
        SysUser user = sysUserService.getSessionUser();
        ModelAndView view = new ModelAndView();
        view.setViewName("user/resetPwd");
        view.addObject("user",sysUserService.selectUserById(user.getId()));
        return view;
    }
}
