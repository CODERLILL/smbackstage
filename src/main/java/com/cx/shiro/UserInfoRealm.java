package com.cx.shiro;

import com.cx.model.UserInfo;
import com.cx.service.impl.UserInfoServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version V1.0
 * @Title: UserInfoRealm.java <br>
 * @Package com.cx.shiro <br>
 * @Description: (shiro处理用户权限自定义realm) <br>
 * @author: 李会龙 <br>
 * @date: 2018/5/18 09:26 <br>
 */
public class UserInfoRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoServiceImpl UserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(UserService.getRoles(username));
        authorizationInfo.setStringPermissions(UserService.getPermissions(username));

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();//获取用户名
        char[] pwd = upToken.getPassword();//获取密码
        String password = String.valueOf(pwd);
        AuthenticationInfo info = new SimpleAuthenticationInfo(username,password,"myRealm");
        return  info;
//        UserInfo user = UserService.isUser(username);
//        if (user!=null){
//            return info;
//        }else {
//            return null;
//        }
    }
}
