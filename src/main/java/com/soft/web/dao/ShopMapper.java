package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShopMapper extends BaseMapper<Shop> {

    List<Shop> getlist(Shop shop);

    int deleteByIds(Long[] userIds);

    int enable(Long id, Integer status);
}
