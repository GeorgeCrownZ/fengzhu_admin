package com.soft.web.service;

import com.soft.web.annotation.OprateLogAOP;
import com.soft.web.dao.ProductCategoryMapper;
import com.soft.web.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("category")
public class ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;


    public List<ProductCategory> getall() {
        return productCategoryMapper.getall();
    }

    public List<ProductCategory> getallwithblank() {
        List<ProductCategory> list = productCategoryMapper.getall();
        ProductCategory pc = new ProductCategory();
        pc.setCategoryName("请选择");
        pc.setId(0L);
        list.add(0, pc);
        return list;
    }

    public List<ProductCategory> productCategoryList(ProductCategory productCategory) {
        return productCategoryMapper.productCategoryList(productCategory);
    }

    public int goodskindshow(Long id, Integer status) {
        return productCategoryMapper.goodskindshow(id,status);
    }

    public ProductCategory selectCategoryById(Long pid) {
        return productCategoryMapper.selectById(pid);
    }

    @OprateLogAOP(model = "商品品类",method = "新增商品品类", remark = "#productCategory.categoryName")
    public int kindaddsave(ProductCategory productCategory) {
        return productCategoryMapper.insert(productCategory);
    }

    @OprateLogAOP(model = "商品品类",method = "修改商品品类", remark = "#productCategory.categoryName")
    public int kindeditsave(ProductCategory productCategory) {
        return productCategoryMapper.updateById(productCategory);
    }

    @OprateLogAOP(model = "商品品类",method = "删除商品品类", remark = "#id")
    public int kindremove(Long id) {
        List<ProductCategory> sublist = productCategoryMapper.getSubCategory(id);
        if(sublist.size()>0){
            return 0;
        }
        return productCategoryMapper.deleteById(id);
    }
}
