package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.ProductPic;
import com.soft.web.entity.ProductSpec;
import com.soft.web.entity.Spec;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SpecMapper extends BaseMapper<Spec> {
}
