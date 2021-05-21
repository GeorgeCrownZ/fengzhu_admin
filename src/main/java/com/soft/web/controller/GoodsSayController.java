package com.soft.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soft.web.common.AjaxResult;
import com.soft.web.common.MyListUtils;
import com.soft.web.common.PageDomain;
import com.soft.web.dao.GoodsSayMapper;
import com.soft.web.entity.GoodsSay;
import com.soft.web.entity.Product;
import com.soft.web.service.GoodsSayService;
import com.soft.web.util.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class GoodsSayController {
    @Autowired
    private GoodsSayService goodsSayService;

    @RequiresPermissions("goodssay:view")
    @GetMapping("goods/goodssay")
    public ModelAndView goodssay()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("goods/goodssay");
        return view;
    }

    @PostMapping("goods/saylist")
    public Object list(PageDomain pageDomain,String searchkey)
    {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderBy());
        List<GoodsSay> list = goodsSayService.goodsSaylist(searchkey);
        TableDataInfo dataInfo = MyListUtils.getDataTable(list);
        return dataInfo;
    }

    @PostMapping("goods/getbacksay")
    public GoodsSay getbacksay(Long pid)
    {
        GoodsSay goodsSay = goodsSayService.getbacksay(pid);
        return goodsSay;
    }

    @PostMapping("goods/beautify")
    public AjaxResult beautify(Long id,Integer status)
    {
        int r = goodsSayService.beautify(id,status);
        return AjaxResult.toAjax(r);
    }

    @PostMapping("goods/showhide")
    public AjaxResult showhide(Long id,Integer status)
    {
        int r = goodsSayService.showhide(id,status);
        return AjaxResult.toAjax(r);
    }

    @GetMapping("goods/replysay")
    public ModelAndView replysay(Integer id)
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("goods/replysay");
        view.addObject("id",id);
        return view;
    }

    @PostMapping("goods/replysaysave")
    public AjaxResult replysaysave(Long id,String content)
    {
        List<GoodsSay> list = goodsSayService.getbacksayList(id);
        if(list.size()>0){
            return AjaxResult.error("已经回复了！");
        }
        int r = goodsSayService.replysaysave(id,content);
        return AjaxResult.toAjax(r);
    }
}
