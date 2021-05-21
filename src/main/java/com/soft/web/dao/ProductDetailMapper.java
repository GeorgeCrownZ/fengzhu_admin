package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.ProductDetail;
import com.soft.web.entity.ProductPic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductDetailMapper extends BaseMapper<ProductDetail> {

    Integer getMaxSortNumber(Long pid);

    List<ProductDetail> getGoodsDetail(Long pid);
}
