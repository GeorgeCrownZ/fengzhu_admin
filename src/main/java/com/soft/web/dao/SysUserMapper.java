package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.SysUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("select * from sys_user where username= #{username} and `password` = #{password}")
    SysUser getUserbyNamePassword(@Param("username") String username, @Param("password") String password);

    @Select({"<script>",
            "SELECT * from sys_user where 1 = 1 ",
            "<if test='username!=null and username!=\"\"  '>",
            " and username like '%${username}%' ",
            "</if>",
            "<if test='phone!=null and phone!=\"\"  '>",
            " AND phone like '%${phone}%' ",
            "</if>",
            "</script>"})
    List<SysUser> selectUserList(SysUser user);

    @Delete({"<script>",
            "delete from sys_user where id in ",
            "<foreach collection='array' item='id' open='(' separator=',' close=')'>#{id}</foreach>",
            "</script>"})
    int deleteUserByIds(Long[] userIds);
}
