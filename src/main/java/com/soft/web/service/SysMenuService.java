package com.soft.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft.web.annotation.OprateLogAOP;
import com.soft.web.common.Ztree;
import com.soft.web.constant.UserConstants;
import com.soft.web.dao.SysMenuMapper;
import com.soft.web.dao.SysRoleMapper;
import com.soft.web.dao.SysRoleMenuMapper;
import com.soft.web.dao.SysUserMapper;
import com.soft.web.entity.SysMenu;
import com.soft.web.entity.SysRole;
import com.soft.web.entity.SysUser;
import com.soft.web.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    public List<SysMenu> getRoleMenu(Long roleid){
        SysRole role = sysRoleMapper.selectById(roleid);
        List<SysMenu> menulist=null;
        if("admin".equals(role.getRolename())){
            menulist = sysMenuMapper.getAllMenu();
        }
        else {
            menulist = sysMenuMapper.getRoleMenu(roleid);
        }
        return menulist;
    }

    public Set<String> selectPermsByUserId(Long userid) {
        List<String> perms = sysMenuMapper.selectPermsByUserId(userid);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    public String transMenuName(SysMenu menu, boolean permsFlag)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getFullname());
        if (permsFlag)
        {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }

    /**
     * ??????????????????
     *
     * @param menuList ????????????
     * @param roleMenuList ???????????????????????????
     * @param permsFlag ??????????????????????????????
     * @return ???????????????
     */
    public List<Ztree> initZtree(List<SysMenu> menuList, List<String> roleMenuList, boolean permsFlag)
    {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleMenuList);
        for (SysMenu menu : menuList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(menu.getId());
            ztree.setpId(menu.getPid());
            ztree.setName(transMenuName(menu, permsFlag));
            ztree.setTitle(menu.getFullname());
            if (isCheck)
            {
                ztree.setChecked(roleMenuList.contains(menu.getId() + menu.getPerms()));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    /**
     * ??????????????????
     *
     * @return ??????????????????
     */
    public List<SysMenu> selectMenuAll(Long userId)
    {
        List<SysMenu> menuList = null;
        SysUser user = sysUserMapper.selectById(userId);
        if ("admin".equals(user.getUsername()))
        {
            menuList = sysMenuMapper.getAllMenu();
        }
        else
        {
            menuList = sysMenuMapper.selectMenuAllByUserId(userId);
        }
        return menuList;
    }

    /**
     * ????????????ID????????????
     *
     * @param role ????????????
     * @return ????????????
     */
    public List<Ztree> roleMenuTreeData(SysRole role, Long userId) {
        Long roleId = role.getId();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<SysMenu> menuList = selectMenuAll(userId);
        if (StringUtils.isNotNull(roleId))
        {
            List<String> roleMenuList = sysMenuMapper.selectMenuTree(roleId);
            ztrees = initZtree(menuList, roleMenuList, true);
        }
        else
        {
            ztrees = initZtree(menuList, null, true);
        }
        for (Ztree t:ztrees){
            if(t.getpId()==null){
                t.setpId(0L);
            }
        }
        return ztrees;
    }

    public List<SysMenu> selectMenuList(SysMenu menu, SysUser user) {
        List<SysMenu> menulist = sysMenuMapper.selectMenuList(menu);
        List<Long> plist = null;
        if ("admin".equals(user.getUsername())) {
            plist = sysMenuMapper.selectAllMenuid();
        } else {
            plist = sysMenuMapper.selectMenuidByUserId(user.getId());
        }
        List<SysMenu> ret = new ArrayList<>();
        for(SysMenu m : menulist){
            if(plist.contains(m.getId())){
                ret.add(m);
            }
        }
        return ret;
    }

    public SysMenu selectMenuById(Long menuid) {
        SysMenu menu = sysMenuMapper.selectById(menuid);
        if(menu.getPid()!=0L){
            SysMenu parentmenu = sysMenuMapper.selectById(menu.getPid());
            menu.setParentname(parentmenu.getFullname());
        }
        return menu;
    }

    public String checkMenuNameUnique(SysMenu menu) {
        Long menuId = StringUtils.isNull(menu.getId()) ? -1L : menu.getId();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("fullname",menu.getFullname());
        SysMenu dbmenu = sysMenuMapper.selectOne(wrapper);
        if (dbmenu!=null)
        {
            if(menu.getId().longValue() != menuId.longValue()) {
                return UserConstants.MENU_NAME_NOT_UNIQUE;
            }
        }
        return UserConstants.MENU_NAME_UNIQUE;
    }

    @OprateLogAOP(model = "??????",method = "????????????", remark = "#menu.fullname")
    public int insertMenu(SysMenu menu) {
        menu.setAddtime(new Date());
        menu.setUpdatetime(new Date());
        int r = sysMenuMapper.insert(menu);
        return r;
    }

    @OprateLogAOP(model = "??????",method = "????????????", remark = "#menu.fullname")
    public int updateMenu(SysMenu menu) {
        menu.setUpdatetime(new Date());
        int r = sysMenuMapper.updateById(menu);
        return r;
    }

    public Integer selectCountMenuByParentId(Long id) {
        try {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("pid",id);
            Integer count = sysMenuMapper.selectCount(wrapper);
            return count;
        } catch (Exception e) {
            return 0;
        }
    }


    public Integer selectCountRoleMenuByMenuId(Long menuid) {
        try {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("menuid",menuid);
            Integer count = sysRoleMenuMapper.selectCount(wrapper);
            return count;
        } catch (Exception e) {
            return 0;
        }
    }

    @OprateLogAOP(model = "??????",method = "????????????", remark = "#id")
    public int deleteMenuById(Long id) {
        int r = sysMenuMapper.deleteById(id);
        return r;
    }

    public List<Ztree> menuTreeData(Long userid) {
        List<SysMenu> menuList = selectMenuAll(userid);
        List<Ztree> ztrees = initZtree(menuList);
        return ztrees;
    }

    /**
     * ??????????????????
     *
     * @param menuList ????????????
     * @return ???????????????
     */
    public List<Ztree> initZtree(List<SysMenu> menuList)
    {
        return initZtree(menuList, null, false);
    }
}
