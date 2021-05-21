package com.soft.web.controller;

import com.github.pagehelper.PageHelper;
import com.soft.web.common.AjaxResult;
import com.soft.web.common.MyListUtils;
import com.soft.web.common.PageDomain;
import com.soft.web.entity.*;
import com.soft.web.service.*;
import com.soft.web.util.FileUploadUtils;
import com.soft.web.util.Global;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPicService productPicService;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private SpecService specService;

    @RequiresPermissions("goods:view")
    @GetMapping("goods")
    public ModelAndView goods()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("goods/goods");
        return view;
    }

    @RequiresPermissions("goods:list")
    @PostMapping("list")
    public Object list(PageDomain pageDomain, Product product)
    {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderBy());
        List<Product> list = productService.goodslist(product);
        return MyListUtils.getDataTable(list);
    }

    @RequestMapping("goodsupdown")  //上架下架
    public AjaxResult goodsupdown(Long pid, Integer status)
    {
        return AjaxResult.toAjax(productService.goodsupdown(pid,status));
    }

    @RequestMapping("goodsspec")  //每日特价
    public AjaxResult goodsspec(Long pid, Integer status)
    {
        return AjaxResult.toAjax(productService.goodsspec(pid,status));
    }

    @RequiresPermissions("goods:batchupdown")  //批量上架下架
    @RequestMapping("batchupdown")
    public AjaxResult batchupdown(String ids, Integer status)
    {
        return AjaxResult.toAjax(productService.batchupdown(ids,status));
    }

    @RequiresPermissions("goods:remove")
    @PostMapping("/remove")
    public AjaxResult remove(String ids)
    {
        try
        {
            return AjaxResult.toAjax(productService.deleteProductByIds(ids));
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    @RequiresPermissions("goods:add")
    @GetMapping("add")
    public ModelAndView add()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("goods/add");
        view.addObject("specs", specService.selectSpec());
        return view;
    }

    @PostMapping("/addsave")
    public AjaxResult addsave(Product product)
    {
        return AjaxResult.toAjax(productService.addsave(product));
    }

    @RequiresPermissions("goods:edit")
    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id)
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("goods/edit");
        view.addObject("product", productService.getbyid(id));
        view.addObject("specs", specService.getSpecByProductId(id));
        return view;
    }

    @PostMapping("/editsave")
    public AjaxResult editsave(Product product)
    {
        return AjaxResult.toAjax(productService.editsave(product));
    }

    @RequestMapping("indexshow")  //首页展示
    public AjaxResult indexshow(Long id,Integer status){
        return AjaxResult.toAjax(productService.indexshow(id,status));
    }

    @PostMapping("/picupload")
    public Object picupload(@RequestParam("pid") Long pid,@RequestParam("showpic") MultipartFile showpic)
    {
        System.out.println("pid:"+pid);
        try
        {
            if (!showpic.isEmpty())
            {
                String picpath = FileUploadUtils.upload(Global.getGoodsPic(), showpic);
                productPicService.addShowPic(pid,picpath);
                List<ProductPic> gdpic = productPicService.getGoodsPic(pid);
                return gdpic;
            }
            return AjaxResult.error();
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    @PostMapping("/goodsdetailupload")
    public Object goodsdetailupload(@RequestParam("pid") Long pid,@RequestParam("detpic") MultipartFile detpic)
    {
        System.out.println("pid:"+pid);
        try
        {
            if (!detpic.isEmpty())
            {
                String picpath = FileUploadUtils.upload(Global.getGoodsDetail(), detpic);
                productDetailService.addDetailPic(pid,picpath);
                List<ProductDetail> detailList = productDetailService.getGoodsDetail(pid);
                return detailList;
            }
            return AjaxResult.error();
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    @PostMapping("/getgoodspic")
    public Object getGoodsPic(Long pid){
        List<ProductPic> gdpic = productPicService.getGoodsPic(pid);
        return gdpic;
    }

    @PostMapping("/getgoodsdetail")
    public Object getgoodsdetail(Long pid){
        List<ProductDetail> detaillist = productDetailService.getGoodsDetail(pid);
        return detaillist;
    }

    @PostMapping("/delgoodspic")
    public Object delgoodspic(Long picid){
        ProductPic productPic = productPicService.getByid(picid);
        productPicService.delFile(productPic.getPicPath());
        productPicService.delGoodsPic(picid);
        List<ProductPic> gdpic = productPicService.getGoodsPic(productPic.getProductId());
        return gdpic;
    }

    @PostMapping("/delgoodsdetail")
    public Object delgoodsdetail(Long picid){
        ProductDetail productDetail = productDetailService.getByid(picid);
        productDetailService.delFile(productDetail.getDetailPath());
        productDetailService.delGoodsDetail(picid);
        List<ProductDetail> list = productDetailService.getGoodsDetail(productDetail.getProductId());
        return list;
    }

    @RequiresPermissions("goodstag:view")
    @GetMapping("goodstag")
    public ModelAndView goodstag()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("goods/goodstag");
        return view;
    }

    @RequiresPermissions("goodsbrand:view")
    @GetMapping("goodsbrand")
    public ModelAndView goodsbrand()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("goods/goodsbrand");
        return view;
    }
}
