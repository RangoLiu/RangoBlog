package com.example.backend.shiro;

import com.example.backend.dto.User;
import com.example.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

@Slf4j
public class MyRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;


    /**
     * 授权
     *
     * @param principalCollection 身份集合
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     *
     * @param authenticationToken 令牌
     * @return 认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) authenticationToken;
        //读取数据库的数据
        User user = userService.login(usernamePasswordToken.getUsername());
        if(user!=null){
            return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        }
        log.warn("账号不存在");
        return null;
    }
}
