package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.GoodsSay;
import com.soft.web.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface GoodsSayMapper extends BaseMapper<GoodsSay> {

    List<GoodsSay> goodsSaylist(String searchkey);

    GoodsSay getbacksay(Long pid);

    int beautify(Long id, Integer status);

    int showhide(Long id, Integer status);

    List<GoodsSay> getbacksayList(Long id);
}
