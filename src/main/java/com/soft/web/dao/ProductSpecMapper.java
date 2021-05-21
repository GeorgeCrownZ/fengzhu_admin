package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.ProductDetail;
import com.soft.web.entity.ProductSpec;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductSpecMapper extends BaseMapper<ProductSpec> {

    Integer deleteProductSpec(Long productId);

    List<ProductSpec> getProductSpec(Long id);
}
