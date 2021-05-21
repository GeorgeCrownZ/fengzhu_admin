package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.Product;
import com.soft.web.entity.ProductPic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductPicMapper extends BaseMapper<ProductPic> {

    Integer getMaxSortNumber(Long pid);

    List<ProductPic> getGoodsPic(Long pid);
}
