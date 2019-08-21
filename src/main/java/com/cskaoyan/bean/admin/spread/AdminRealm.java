//package com.cskaoyan.bean.admin.spread;
//
//import com.cskaoyan.bean.admin.system.AdminExample;
//import com.cskaoyan.mapper.system.AdminMapper;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.Permission;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.security.Permissions;
//import java.util.List;
//
//@Component
//public class AdminRealm extends AuthorizingRealm {
//    @Autowired
//    AdminMapper adminMapper;
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        String usernmae= (String) principalCollection.getPrimaryPrincipal();
//        List<String> permissions=adminMapper.getPermissionsByName(usernmae);
//        authorizationInfo.addStringPermissions(permissions);
//        return authorizationInfo;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        String username= (String) authenticationToken.getPrincipal();
//        String password=adminMapper.getPassWordByName(username);
//        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,password,"adminRealm");
//        return simpleAuthenticationInfo;
//    }
//}
