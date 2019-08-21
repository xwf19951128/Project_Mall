package com.cskaoyan.config;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class MallSessionManager extends DefaultWebSessionManager {

    @Override
    protected Serializable getSessionId(ServletRequest servletRequest, ServletResponse response) {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String id = request.getHeader("X-cskaoyanmall-Admin-Token");
        String id2 = request.getHeader("X-Litemall-Token");
        if (id != null && !"".equals(id) )
        {
            return id;
        }

        if (id2 != null && !"".equals(id2) )
        {
            return id2;
        }
        return super.getSessionId(servletRequest, response);
    }
}
