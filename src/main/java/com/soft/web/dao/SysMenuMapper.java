package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    @Select("select m.* from sys_menu m left join sys_rolemenu a on m.id=a.menuid where a.roleid=#{roleid} and m.state='1' order by m.sortcode")
    List<SysMenu> getRoleMenu(@Param("roleid") Long roleid);

    @Select("select * from sys_menu where state='1' order by sortcode")
    List<SysMenu> getAllMenu();

    @Select("select m.perms from sys_menu m left join sys_rolemenu a on m.id=a.menuid left join sys_user u on u.roleid=a.roleid where u.id=#{userid} and m.state='1' order by m.sortcode")
    List<String> selectPermsByUserId(@Param("userid") Long userid);

    @Select("select m.* from sys_menu m left join sys_rolemenu a on m.id=a.menuid left join sys_user u on u.roleid=a.roleid where u.id=#{userid} and m.state='1' order by m.sortcode")
    List<SysMenu> selectMenuAllByUserId(@Param("userid") Long userid);

    @Select("select concat(m.id, ifnull(m.perms,'')) as perms " +
            " from sys_menu m" +
            " left join sys_rolemenu rm on m.id = rm.menuid" +
            " where rm.roleid = #{roleid}" +
            " order by m.pid, m.sortcode")
    List<String> selectMenuTree(@Param("roleid") Long roleid);

    @Select({"<script>",
            "SELECT * from sys_menu where 1 = 1 ",
            "<if test='fullname!=null and fullname!=\"\"  '>",
            " and fullname like '%${fullname}%' ",
            "</if>",
            "<if test='state!=null and state!=\"\"  '>",
            " AND state = #{state} ",
            "</if>",
            " order by sortcode",
            "</script>"})
    List<SysMenu> selectMenuList(SysMenu menu);

    @Select("select id from sys_menu order by sortcode")
    List<Long> selectAllMenuid();

    @Select("select m.id from sys_menu m " +
            "left join sys_rolemenu a on m.id=a.menuid " +
            "left join sys_user u on u.roleid=a.roleid " +
            "where u.id=#{userid} and m.state='1' order by m.sortcode")
    List<Long> selectMenuidByUserId(@Param("userid") Long userid);
}
