package com.soft.web.controller;

import com.github.pagehelper.PageHelper;
import com.soft.web.common.AjaxResult;
import com.soft.web.common.MyListUtils;
import com.soft.web.common.PageDomain;
import com.soft.web.entity.ProductCategory;
import com.soft.web.entity.RollPic;
import com.soft.web.entity.Shop;
import com.soft.web.service.ShopService;
import com.soft.web.util.FileUploadUtils;
import com.soft.web.util.Global;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @RequiresPermissions("shop:view")
    @RequestMapping("shop")
    public ModelAndView shop(){
        ModelAndView view = new ModelAndView();
        view.setViewName("shop/shop");
        return view;
    }

    @PostMapping("list")
    public Object list(PageDomain pageDomain, Shop shop) {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize());
        PageHelper.orderBy(pageDomain.getOrderBy());
        List<Shop> list = shopService.shoplist(shop);
        return MyListUtils.getDataTable(list);
    }

    @RequestMapping("add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView();
        view.setViewName("shop/add");
        return view;
    }

    @PostMapping("addsave")
    public Object addsave(Shop shop, @RequestParam("pic") MultipartFile pic) {
        try
        {
            if (!pic.isEmpty())
            {
                String urlimg = FileUploadUtils.upload(Global.getShopPath(), pic);
                shop.setShopPic(urlimg);
                shop.setStatus(1);
                int r= shopService.addsave(shop);
                return  AjaxResult.toAjax(r);
            }
            else{
                return AjaxResult.error();
            }
        }
        catch(Exception ex)
        {
            return AjaxResult.error();
        }
    }

    @RequestMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        Shop shop = shopService.selectShopById(id);
        ModelAndView view = new ModelAndView();
        view.setViewName("shop/edit");
        view.addObject("shop",shop);
        return view;
    }

    @PostMapping("editsave")
    public AjaxResult kindeditsave(Shop shop, @RequestParam("pic") MultipartFile pic)
    {
        try
        {
            if (!pic.isEmpty())
            {
                String urlimg = FileUploadUtils.upload(Global.getCategory(), pic);
                shop.setShopPic(urlimg);
            }
            int r= shopService.editsave(shop);
            return  AjaxResult.toAjax(r);
        }
        catch(Exception ex)
        {
            return AjaxResult.error();
        }
    }

    @PostMapping("/remove")
    public AjaxResult remove(String ids) {
        try
        {
            return AjaxResult.toAjax(shopService.remove(ids));
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    @PostMapping("enable")
    public AjaxResult enable(Long id, Integer status)
    {
        int r=shopService.enable(id,status);
        return AjaxResult.toAjax(r);
    }

}
