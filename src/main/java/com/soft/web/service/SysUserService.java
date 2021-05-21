package com.soft.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft.web.annotation.OprateLogAOP;
import com.soft.web.common.PageDomain;
import com.soft.web.constant.UserConstants;
import com.soft.web.dao.SysRoleMapper;
import com.soft.web.dao.SysUserMapper;
import com.soft.web.entity.SysRole;
import com.soft.web.entity.SysUser;
import com.soft.web.util.Convert;
import com.soft.web.util.StringUtils;
import com.soft.web.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private HttpSession session;

    public SysUser check(SysUser user) {
        String md5pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        SysUser sysUser = sysUserMapper.getUserbyNamePassword(user.getUsername(),md5pwd);
        return sysUser;
    }

    public SysUser getUserByName(String username) {
        QueryWrapper<SysUser> wrapper=new QueryWrapper<>();
        wrapper.eq("username",username);
        SysUser dbuser = sysUserMapper.selectOne(wrapper);
        return dbuser;
    }

    public List<SysUser> selectUserList(SysUser user) {
        List<SysUser> list = sysUserMapper.selectUserList(user);
        for (SysUser u:list) {
            u.setRolename(sysRoleMapper.selectById(u.getRoleid()).getRolename());
        }
        return list;
    }

    public String checkLoginNameUnique(String username) {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        SysUser user = sysUserMapper.selectOne(wrapper);
        if (user!=null){
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    public String checkPhoneUnique(SysUser user) {
        Long userId = user.getId()==null?0L:user.getId();
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("phone",user.getPhone());
        SysUser dbuser = sysUserMapper.selectOne(wrapper);
        if (dbuser!=null) {
            if(dbuser.getId()!=userId){
                return UserConstants.USER_PHONE_NOT_UNIQUE;
            }
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }

    public String checkEmailUnique(SysUser user) {
        Long userId = user.getId() == null ? 0L : user.getId();
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("email", user.getEmail());
        SysUser dbuser = sysUserMapper.selectOne(wrapper);
        if (dbuser != null) {
            {
                if (dbuser.getId() != userId) {
                    return UserConstants.USER_EMAIL_NOT_UNIQUE;
                }
            }
        }
        return UserConstants.USER_EMAIL_UNIQUE;
    }

    @OprateLogAOP(model = "系统",method = "新增管理员", remark = "#user.username")
    public int insertUser(SysUser user) {
        int r = sysUserMapper.insert(user);
        return r;
    }

    public Object selectUserById(Long userId) {
        SysUser user = sysUserMapper.selectById(userId);
        return user;
    }

    @OprateLogAOP(model = "系统",method = "修改管理员", remark = "#user.username")
    public int updateUser(SysUser user) {
        SysUser dbuser = sysUserMapper.selectById(user.getId());
        if(StringUtils.isEmpty(user.getPassword())){
            user.setPassword(dbuser.getPassword());
        }
        else{
            String md5pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            user.setPassword(md5pwd);
        }
        int r = sysUserMapper.updateById(user);
        return r;
    }

    @OprateLogAOP(model = "系统",method = "删除管理员", remark = "#ids")
    public int deleteUserByIds(String ids) {
        Long[] userIds = Convert.toLongArray(ids);
        return sysUserMapper.deleteUserByIds(userIds);
    }

    public SysUser getSessionUser() {
        SysUser user = null;
        try {
            user = (SysUser) session.getAttribute("user");
        } catch (Exception e) {  }
        return user;
    }

    public Object selectUserRoleGroup(Long userid) {
        List<SysRole> list = sysRoleMapper.selectRolesByUserId(userid);
        StringBuffer idsStr = new StringBuffer();
        for (SysRole role : list)
        {
            idsStr.append(role.getRolename()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    public Integer updateUserInfo(SysUser currentUser) {
        int r = sysUserMapper.updateById(currentUser);
        return r;
    }

    public boolean matchesPassword(SysUser user, String password) {
        String md5pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        return user.getPassword().equals(md5pwd);
    }

    public Integer resetUserPwd(SysUser user, String newPassword) {
        String md5pwd = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        user.setPassword(md5pwd);
        int r = sysUserMapper.updateById(user);
        return r;

    }

    public String importUser(List<SysUser> userList, boolean updateSupport, String operName) {
        for (SysUser user : userList){
            List<SysUser> list = sysUserMapper.selectList(new QueryWrapper<SysUser>().eq("username",user.getUsername()));
            if(list.size()>0){
                user.setUsername(user.getUsername()+"_"+(list.size()+1));
            }
            user.setRoleid(1L);
            user.setSortcode(999);
            sysUserMapper.insert(user);
        }
        return "导入成功";
    }


}
