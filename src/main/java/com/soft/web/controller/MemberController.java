package com.soft.web.controller;

import com.github.pagehelper.PageHelper;
import com.soft.web.common.AjaxResult;
import com.soft.web.common.MyListUtils;
import com.soft.web.common.PageDomain;
import com.soft.web.entity.Channel;
import com.soft.web.entity.Member;
import com.soft.web.entity.Product;
import com.soft.web.entity.UserAddress;
import com.soft.web.service.MemberService;
import com.soft.web.util.FileUploadUtils;
import com.soft.web.util.Global;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequiresPermissions("member:view")
    @GetMapping("member")
    public ModelAndView member()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/member");
        return view;
    }

    @PostMapping("list")
    public Object list(PageDomain pageDomain, String searchkey)
    {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderBy());
        List<Member> list = memberService.memberlist(searchkey);
        return MyListUtils.getDataTable(list);
    }

    @PostMapping("enable")
    public AjaxResult enable(Long id, Integer status)
    {
        int r=memberService.enable(id,status);
        return AjaxResult.toAjax(r);
    }

    @GetMapping("add")
    public ModelAndView add()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/add");
        return view;
    }

    @RequestMapping("addsave")
    public AjaxResult addsave(Member member, @RequestParam("hpic") MultipartFile hpic)
    {
        try
        {
            if (!hpic.isEmpty())
            {
                String picpath = FileUploadUtils.upload(Global.getUser(), hpic);
                member.setHeadPic(picpath);
            }
            int r= memberService.addsave(member);
            return  AjaxResult.toAjax(r);
        }
        catch(Exception ex)
        {
            return AjaxResult.error();
        }
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id)
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/edit");
        Member member = memberService.getByid(id);
        view.addObject("member",member);
        return view;
    }

    @RequestMapping("editsave")
    public AjaxResult editsave(Member member, @RequestParam("hpic") MultipartFile hpic)
    {
        try
        {
            if (!hpic.isEmpty())
            {
                String picpath = FileUploadUtils.upload(Global.getUser(), hpic);
                member.setHeadPic(picpath);
            }
            int r= memberService.editsave(member);
            return  AjaxResult.toAjax(r);
        }
        catch(Exception ex)
        {
            return AjaxResult.error();
        }
    }

    @RequestMapping("remove")
    public AjaxResult remove(String ids)
    {
        try
        {
            return AjaxResult.toAjax(memberService.deleteByIds(ids));
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    @RequestMapping("employee")
    public AjaxResult employee(Long id,Integer status)
    {
        try
        {
            return AjaxResult.toAjax(memberService.setEmployee(id, status));
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/detail");
        Member member = memberService.getByid(id);
        Member Pmember = memberService.getByid(member.getPid());
        if(Pmember!=null) {
            member.setMember(Pmember);
        }
        view.addObject("member", member);
        return view;
    }

    @GetMapping("/toaddress/{userId}")
    public ModelAndView toaddress(@PathVariable("userId") Long userId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userId",userId);
        modelAndView.setViewName("member/useraddress");
        return modelAndView;
    }

    @PostMapping("/useraddress/{userId}")
    public Object useraddress(PageDomain pageDomain, @PathVariable("userId") Long userId) {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderBy());
        List<UserAddress> userAddresses = memberService.selectAddressByUserId(userId);
        return MyListUtils.getDataTable(userAddresses);
    }

    @GetMapping("/tomembersay/{id}")
    public ModelAndView tomembersay(@PathVariable("id")Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("member/membersay");
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    @RequestMapping("/employee/{userId}")
    public Object employee(@PathVariable("userId") Long userId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userId",userId);
        modelAndView.setViewName("member/employee");
        return modelAndView;
    }

    @RequestMapping("/employeesave")
    public AjaxResult employeesave(Long id,Long shopId) {
        return AjaxResult.toAjax(memberService.employeesave(id,shopId));
    }

    @GetMapping("/userorders/{id}")
    public ModelAndView userorders(@PathVariable("id")Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("member/memberorders");
        modelAndView.addObject("id", id);
        return modelAndView;
    }
}
