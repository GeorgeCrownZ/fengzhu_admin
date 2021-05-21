package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.Product;
import com.soft.web.entity.ProductParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductParamMapper extends BaseMapper<ProductParam> {

    List<ProductParam> productParamList(Long pid);

    int deleteInBatch(Long[] paramids);
}
