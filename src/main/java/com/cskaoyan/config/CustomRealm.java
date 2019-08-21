package com.cskaoyan.config;

import com.cskaoyan.bean.admin.login.Admin;
import com.cskaoyan.bean.admin.login.AdminInfo;
import com.cskaoyan.service.admin.login.LoginService;
import com.cskaoyan.bean.wx.login.ActiveUser;
import com.cskaoyan.bean.wx.login.WxUser;
import com.cskaoyan.service.wx.login.WxLoginService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    LoginService loginService;
    @Autowired
    WxLoginService wxLoginService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        TypeToken typeToken = (TypeToken) authenticationToken;
        String type = typeToken.getType();
        if (type.equals("admin")) {
            List<Admin> adminList = loginService.queryPasswordByName(username);
            if (adminList.size() == 0) {
                return null;
            } else {
                String password = adminList.get(0).getPassword();
                SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, "customRealm");
                return authenticationInfo;
            }
        } else if (type.equals("wx")) {
            String password = wxLoginService.queryWxPasswordByUsername(username);
            if (password==null){
                return null;
            }else {
                WxUser wxUser = wxLoginService.queryUserByUsername(username);
                ActiveUser activeUser = new ActiveUser(wxUser.getId(),wxUser.getUsername());
                SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser,password,"customRealm");
                return simpleAuthenticationInfo;
            }
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = (String) principalCollection.getPrimaryPrincipal();
        AdminInfo adminInfo = loginService.queryAdminInfoByUsername(username);
        List<String> perms = adminInfo.getPerms();
        List<String> roles = adminInfo.getRoles();
        authorizationInfo.addStringPermissions(perms);
        authorizationInfo.addRoles(roles);
        return authorizationInfo;
    }

    public void clearCache(){
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

}
