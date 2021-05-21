package com.soft.web.common;

import com.soft.web.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    public static SysUser getSessionUser(HttpSession session, String key){
        SysUser user = null;
        try {
            user = (SysUser) session.getAttribute(key);
        } catch (Exception e) {  }
        return user;
    }
}
