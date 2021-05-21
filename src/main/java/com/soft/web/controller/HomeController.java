package com.soft.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.soft.web.annotation.OprateLogAOP;
import com.soft.web.common.AjaxJson;
import com.soft.web.common.SessionUtil;
import com.soft.web.entity.SysMenu;
import com.soft.web.entity.SysUser;
import com.soft.web.service.SysMenuService;
import com.soft.web.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

//    @Autowired
//    private RemoteSysService remoteSysService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping
    public ModelAndView start() {
        ModelAndView view = new ModelAndView();
        view.setViewName("login");
        return view;
    }

    @RequestMapping("login")
    @OprateLogAOP(model = "系统",method = "登录")
    public AjaxJson login(SysUser user, HttpSession session) {
        AjaxJson json = new AjaxJson();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), false);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            SysUser dbuser = sysUserService.check(user);
            if(dbuser==null){
                json.setMsg("登录失败");
                json.setSuccess(false);
                json.setCode(-1);
                return json;
            }
            session.setAttribute("user", dbuser);
            json.setMsg("登录成功");
            json.setSuccess(true);
            json.setCode(0);
        } catch (Exception e) {
            json.setMsg("登录失败");
            json.setSuccess(false);
            json.setCode(-1);
        }
        return json;
    }

    @RequestMapping("index")
    public ModelAndView index(HttpSession session) {
        SysUser user=SessionUtil.getSessionUser(session,"user");
        if(user==null){
            ModelAndView view = new ModelAndView();
            view.setViewName("login");
            return view;
        }

        List<SysMenu> menuList = sysMenuService.getRoleMenu(user.getRoleid());

        List<SysMenu> menus = new ArrayList<SysMenu>();
        for(SysMenu menu : menuList) {
            if(menu.getPid() == null || menu.getPid()==0L ){
//                System.out.println("主菜单："+menu.getFullname());
                List<SysMenu> menuViews = new ArrayList<SysMenu>();
                for(SysMenu m : menuList){
                    if(m.getPid() !=null && m.getPid().equals(menu.getId())){
//                        System.out.println("    子菜单："+m.getFullname());
                        menuViews.add(m);
                    }
                }
                menu.setSonlist(menuViews);
                menus.add(menu);
            }
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        view.addObject("user",user);
        view.addObject("menus",menus);
        return view;
    }

    @RequestMapping("desk")
    public ModelAndView desk() {
        ModelAndView view = new ModelAndView();
        view.setViewName("desk");
        return view;
    }

    // 切换主题
    @GetMapping("/switchSkin")
    public ModelAndView switchSkin()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("skin");
        return view;
    }

}
