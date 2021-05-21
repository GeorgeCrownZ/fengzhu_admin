package com.soft.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft.web.annotation.OprateLogAOP;
import com.soft.web.common.PageDomain;
import com.soft.web.constant.UserConstants;
import com.soft.web.dao.SysRoleMapper;
import com.soft.web.dao.SysRoleMenuMapper;
import com.soft.web.dao.SysUserMapper;
import com.soft.web.entity.SysRole;
import com.soft.web.entity.SysRoleMenu;
import com.soft.web.entity.SysUser;
import com.soft.web.util.BusinessException;
import com.soft.web.util.Convert;
import com.soft.web.util.StringUtils;
import com.soft.web.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    public List<SysRole> selectRoleAll(){
        QueryWrapper wrapper = new QueryWrapper();
        List<SysRole> list = sysRoleMapper.selectList(wrapper);
        return list;
    }

    public List<SysRole> getRolesByUserName(String username) {
        List<SysRole> rolelist = sysRoleMapper.getRolesByUserName(username);
        return rolelist;
    }

    public Set<String> selectRoleKeys(Long userid) {
        List<SysRole> perms = sysRoleMapper.selectRolesByUserId(userid);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                permsSet.addAll(Arrays.asList(perm.getRolekey().trim().split(",")));
            }
        }
        return permsSet;
    }

    public Object selectRolesByUserId(Long userId) {
        List<SysRole> userRoles = sysRoleMapper.selectRolesByUserId(userId);
        List<SysRole> roles = selectRoleAll();
        for (SysRole role : roles)
        {
            for (SysRole userRole : userRoles)
            {
                if (role.getId().equals(userRole.getId()))
                {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

    public List<SysRole> selectRoleList(SysRole role) {
        List<SysRole> list = sysRoleMapper.selectRoleList(role);
        return list;
    }

    public String checkRoleNameUnique(SysRole role) {
        Long roleId = StringUtils.isNull(role.getId()) ? -1L : role.getId();
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("rolename",role.getRolename());
        SysRole dbrole = sysRoleMapper.selectOne(wrapper);
        if (dbrole!=null)
        {
            if(dbrole.getId()!=roleId) {
                return UserConstants.ROLE_NAME_NOT_UNIQUE;
            }
        }
        return UserConstants.ROLE_NAME_UNIQUE;
    }

    public String checkRoleKeyUnique(SysRole role) {
        Long roleId = StringUtils.isNull(role.getId()) ? -1L : role.getId();
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("rolekey",role.getRolekey());
        SysRole dbrole = sysRoleMapper.selectOne(wrapper);
        if (dbrole!=null)
        {
            if(dbrole.getId()!=roleId) {
                return UserConstants.ROLE_KEY_NOT_UNIQUE;
            }
        }
        return UserConstants.ROLE_KEY_UNIQUE;
    }

    @Transactional
    @OprateLogAOP(model = "系统",method = "新增角色", remark = "#role.rolename")
    public int insertRole(SysRole role) {
        int r = sysRoleMapper.insert(role);
        insertRoleMenu(role);
        return r;
    }

    public Object getbyid(Long roleId) {
        SysRole role = sysRoleMapper.selectById(roleId);
        return role;
    }

    /**
     * 新增角色菜单信息
     *
     * @param role 角色对象
     */
    public int insertRoleMenu(SysRole role)
    {
        int rows = 1;
        // 新增用户与角色管理
        List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
        for (Long menuId : role.getMenuIds())
        {
            SysRoleMenu rm = new SysRoleMenu();
            rm.setRoleid(role.getId());
            rm.setMenuid(menuId);
            list.add(rm);
        }
        if (list.size() > 0)
        {
            rows = sysRoleMenuMapper.saveAll(list);
        }
        return rows;
    }

    @Transactional
    @OprateLogAOP(model = "系统",method = "修改角色", remark = "#role.rolename")
    public int updateRole(SysRole role) {
        // 修改角色信息
        sysRoleMapper.updateById(role);
        // 删除角色与菜单关联
        sysRoleMenuMapper.deleteRoleMenuByRoleId(role.getId());
        return insertRoleMenu(role);
    }

    @OprateLogAOP(model = "系统",method = "修改角色", remark = "#ids")
    public int deleteRoleByIds(String ids) {
        Long[] roleIds = Convert.toLongArray(ids);
        List<SysRole> rolelist=new ArrayList();
        for (Long roleId : roleIds)
        {
            SysRole role = sysRoleMapper.selectById(roleId);
            if (countUserRoleByRoleId(roleId) > 0)
            {
                throw new BusinessException(String.format("%1$s已分配,不能删除", role.getRolename()));
            }
            rolelist.add(role);
        }
        sysRoleMapper.deleteInBatch(rolelist);
        // 删除角色与菜单关联
        for (Long roleId : roleIds){
            sysRoleMenuMapper.deleteRoleMenuByRoleId(roleId);
        }
        return 1;
    }

    private Integer countUserRoleByRoleId(Long roleId) {
        SysUser user = new SysUser();
        user.setRoleid(roleId);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("roleid",roleId);
        Integer count = sysUserMapper.selectCount(wrapper);
        return count;
    }
}
