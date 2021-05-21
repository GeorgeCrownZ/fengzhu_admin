package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {

    List<ProductCategory> productCategoryList(ProductCategory productCategory);

    List<ProductCategory> getall();

    int goodskindshow(Long id, Integer status);

    List<ProductCategory> getSubCategory(Long id);
}
