package com.cskaoyan.util.wx;

import javax.servlet.http.HttpServletRequest;

public class TokenUtil{
    public static Integer  getActiveUserid(HttpServletRequest request){
        String token=request.getHeader("X-Litemall-Token");
        return UserTokenManager.getUserId(token);
    }
}
