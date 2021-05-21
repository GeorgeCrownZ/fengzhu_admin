package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SysRoleMapper  extends BaseMapper<SysRole> {

    @Select("SELECT r.* FROM sys_role r left join sys_user u ON u.roleid=r.id where u.username = #{username}")
    List<SysRole> getRolesByUserName(@Param("username") String username);

    @Select("SELECT r.* FROM `sys_role` r left join sys_user u ON u.roleid=r.id where u.id = #{userid}")
    List<SysRole> selectRolesByUserId(@Param("userid") Long userid);

    @Select({"<script>",
            "SELECT * from sys_role where 1 = 1 ",
            "<if test='rolename!=null and rolename!=\"\"  '>",
            " and rolename like '%${rolename}%' ",
            "</if>",
            "<if test='rolekey!=null and rolekey!=\"\"  '>",
            " AND rolekey like '%${rolekey}%' ",
            "</if>",
            "</script>"})
    List<SysRole> selectRoleList(SysRole role);

    @Delete({"<script>",
            "delete from sys_role where id in ",
            "<foreach collection='list' item='item' open='(' separator=',' close=')'>#{item.id}</foreach>",
            "</script>"})
    int deleteInBatch(List<SysRole> rolelist);
}
