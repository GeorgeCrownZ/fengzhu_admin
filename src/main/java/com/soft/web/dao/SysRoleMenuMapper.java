package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SysRoleMenuMapper  extends BaseMapper<SysRoleMenu> {

    @Delete("delete from sys_rolemenu where roleid = #{roleid}")
    int deleteRoleMenuByRoleId(@Param("roleid") Long roleid);

    @Insert({"<script>",
            "insert into sys_rolemenu(roleid, menuid) values",
            "<foreach collection='list' item='item' index='index' separator=','>" +
                    "(#{item.roleid},#{item.menuid})" +
            "</foreach>",
            "</script>"})
    int saveAll(List<SysRoleMenu> list);
}
