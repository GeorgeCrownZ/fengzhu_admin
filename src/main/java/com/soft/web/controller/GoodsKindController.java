package com.soft.web.controller;

import com.soft.web.common.AjaxResult;
import com.soft.web.entity.ProductCategory;
import com.soft.web.service.ProductCategoryService;
import com.soft.web.util.FileUploadUtils;
import com.soft.web.util.Global;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class GoodsKindController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequiresPermissions("goodskind:view")
    @GetMapping("goods/goodskind")
    public ModelAndView goodskind()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("goods/goodskind");
        return view;
    }

    @RequestMapping("goods/goodskindshow")  //品类显示
    public AjaxResult goodskindshow(Long id, Integer status)
    {
        return AjaxResult.toAjax(productCategoryService.goodskindshow(id,status));
    }

    @PostMapping("goods/kindlist")
    public List<ProductCategory> kindlist(ProductCategory productCategory)
    {
        List<ProductCategory> list = productCategoryService.productCategoryList(productCategory);
        return list;
    }

    @GetMapping("goods/kindadd/{pid}")
    public ModelAndView kindadd(@PathVariable("pid") Long pid)
    {
        ProductCategory category = null;
        if (0L != pid)
        {
            category = productCategoryService.selectCategoryById(pid);
        }
        else
        {
            category = new ProductCategory();
            category.setId(0L);
            category.setCategoryName("");
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("goods/kindadd");
        view.addObject("pc",category);
        return view;
    }

    @PostMapping("goods/kindaddsave")
    public AjaxResult kindaddsave(ProductCategory productCategory, @RequestParam("logopic") MultipartFile logopic)
    {
        try
        {
            if (!logopic.isEmpty())
            {
                String urlimg = FileUploadUtils.upload(Global.getCategory(), logopic);
                productCategory.setUrlimg(urlimg);
                productCategory.setStatus(1);
                int r= productCategoryService.kindaddsave(productCategory);
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

    @GetMapping("goods/kindedit/{id}")
    public ModelAndView kindedit(@PathVariable("id") Long id)
    {
        ProductCategory category = productCategoryService.selectCategoryById(id);
        ModelAndView view = new ModelAndView();
        view.setViewName("goods/kindedit");
        view.addObject("pc",category);
        return view;
    }

    @PostMapping("goods/kindeditsave")
    public AjaxResult kindeditsave(ProductCategory productCategory, @RequestParam("logopic") MultipartFile logopic)
    {
        try
        {
            if (!logopic.isEmpty())
            {
                String urlimg = FileUploadUtils.upload(Global.getCategory(), logopic);
                productCategory.setUrlimg(urlimg);
            }
            int r= productCategoryService.kindeditsave(productCategory);
            return  AjaxResult.toAjax(r);

        }
        catch(Exception ex)
        {
            return AjaxResult.error();
        }
    }

    @RequestMapping("goods/kindremove/{id}")
    public AjaxResult kindremove(@PathVariable("id") Long id)
    {
        try
        {
            int r= productCategoryService.kindremove(id);
            return  AjaxResult.toAjax(r);
        }
        catch(Exception ex)
        {
            return AjaxResult.error();
        }
    }
}
