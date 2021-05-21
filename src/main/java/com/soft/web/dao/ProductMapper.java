package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    @Select({"<script>",
            "SELECT * from fa_productinfo where 1 = 1 ",
            "<if test='productName!=null and productName!=\"\"  '>",
            " and product_name like '%${productName}%' ",
            "</if>",
            "</script>"})
    List<Product> goodslist(Product product);

    int batchup(Long[] ids);

    int batchdown(Long[] ids);

    void deleteInBatch(List<Product> productlist);
}
