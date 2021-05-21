package com.soft.web.service;

import com.soft.web.annotation.OprateLogAOP;
import com.soft.web.dao.ProductMapper;
import com.soft.web.dao.ProductSpecMapper;
import com.soft.web.dao.SpecMapper;
import com.soft.web.entity.Product;
import com.soft.web.entity.ProductSpec;
import com.soft.web.entity.Spec;
import com.soft.web.entity.SysRole;
import com.soft.web.util.BusinessException;
import com.soft.web.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private ProductSpecMapper productSpecMapper;

    public List<Product> goodslist(Product product) {
        List<Product> list = productMapper.goodslist(product);
        return list;
    }

    @OprateLogAOP(model = "商品管理",method = "上下架",remark = "#pid")
    public int goodsupdown(Long pid, Integer status) {
        Product product = productMapper.selectById(pid);
        product.setUpdownStatus(status);
        product.setUpdownTime(new Date());
        return productMapper.updateById(product);
    }

    @OprateLogAOP(model = "商品管理",method = "每日特价",remark = "#pid")
    public int goodsspec(Long pid, Integer status) {
        Product product = productMapper.selectById(pid);
        product.setSpecialoffer(status);
        product.setUpdownTime(new Date());
        return productMapper.updateById(product);
    }

    @OprateLogAOP(model = "商品管理",method = "批量上下架",remark = "#ids")
    public int batchupdown(String ids, Integer status) {
        Long[] pids = Convert.toLongArray(ids);
        if(status==1){
            return productMapper.batchup(pids);
        }
        else if (status==0){
            return productMapper.batchdown(pids);
        }
        else {
            return 0;
        }

    }

    @OprateLogAOP(model = "商品管理",method = "删除商品",remark = "#ids")
    public int deleteProductByIds(String ids) {
        Long[] pids = Convert.toLongArray(ids);
        List<Product> productlist = new ArrayList();
        for (Long pid : pids)
        {
            Product product = productMapper.selectById(pid);
            //先删除关联的商品图片，详情，规格，标签

            productlist.add(product);
        }
        productMapper.deleteInBatch(productlist);
        return 1;
    }

    @OprateLogAOP(model = "商品管理",method = "添加商品",remark = "#product.productName")
    public int addsave(Product product) {
        int r = productMapper.insert(product);
        //添加新商品标签
        Long specid[] = Convert.toLongArray(product.getSpecid());
        for (Long id : specid) {
            ProductSpec productSpec = new ProductSpec();
            productSpec.setProductId(product.getProductId());
            productSpec.setSpecId(id);
            productSpecMapper.insert(productSpec);
        }
        return r;
    }

    public Product getbyid(Long id) {
        return productMapper.selectById(id);
    }

    @OprateLogAOP(model = "商品管理",method = "修改商品",remark = "#product.productName")
    public int editsave(Product product) {
        //删除原有商品标签
        productSpecMapper.deleteProductSpec(product.getProductId());
        //添加新商品标签
        Long specid[] = Convert.toLongArray(product.getSpecid());
        for (Long id : specid) {
            ProductSpec productSpec = new ProductSpec();
            productSpec.setProductId(product.getProductId());
            productSpec.setSpecId(id);
            productSpecMapper.insert(productSpec);
        }
        return productMapper.updateById(product);
    }

    public int indexshow(Long id, Integer status) {
        Product product = productMapper.selectById(id);
        product.setShowIndex(status);
        return productMapper.updateById(product);
    }
}
