package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.ProductSpec;
import com.soft.web.entity.Spec;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsTagMapper extends BaseMapper<Spec> {

    public List<Spec> selectSpecList();

    public int addSpec(Spec spec);

    public int updateSpec(Spec spec);

    public int deleteSpecByIds(@Param("specList") List<Long> specList);

    public Spec selectSpecById(Long id);

    public int updateStatus(Spec spec);

    public List<ProductSpec> selectCountBySpecId(Long id);
}
