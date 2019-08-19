package com.cskaoyan.config;

import com.cskaoyan.bean.login.Admin;
import com.cskaoyan.bean.login.AdminInfo;
import com.cskaoyan.service.login.LoginService;
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

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        List<Admin> adminList = loginService.queryPasswordByName(username);
        if (adminList.size()==0){
            return null;
        }else {
            String password = adminList.get(0).getPassword();
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, "customRealm");
            return authenticationInfo;
        }
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
