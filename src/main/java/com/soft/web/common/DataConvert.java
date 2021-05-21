package com.soft.web.common;

import com.alibaba.fastjson.JSONObject;
import com.soft.web.entity.SysRole;
import com.soft.web.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataConvert {
    public static Set<String> JsonToSysRoleSet(String json){
        List<SysRole> perms = JSONObject.parseArray(json, SysRole.class);
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

    public static Set<String> JsonToSysMenuSet(String json){
        List<String> perms = JSONObject.parseArray(json, String.class);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }
}
