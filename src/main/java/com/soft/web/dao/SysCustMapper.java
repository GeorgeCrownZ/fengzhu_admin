package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.SysCust;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SysCustMapper extends BaseMapper<SysCust> {
    @Select({"<script>",
            "SELECT * from sys_cust where 1 = 1 ",
            "<if test='custname!=null and custname!=\"\"  '>",
            " and custname like '%${custname}%' ",
            "</if>",
            "<if test='custphone!=null and custphone!=\"\"  '>",
            " AND custphone like '%${custphone}%' ",
            "</if>",
            "</script>"})
    List<SysCust> selectCustList(SysCust cust);

    @Delete({"<script>",
            "delete from sys_cust where id in ",
            "<foreach collection='custIds' item='id' open='(' separator=',' close=')'>#{id}</foreach>",
            "</script>"})
    int deleteCustByIds(Long[] custIds);
}
