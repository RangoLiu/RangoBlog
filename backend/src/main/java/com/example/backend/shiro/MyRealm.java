package com.example.backend.shiro;

import com.example.backend.dto.User;
import com.example.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

@Slf4j
public class MyRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) authenticationToken;
        User user = userService.login(usernamePasswordToken.getUsername());
        if(user!=null){
            return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        }
        log.warn("账号不存在");
        return null;
    }
}
