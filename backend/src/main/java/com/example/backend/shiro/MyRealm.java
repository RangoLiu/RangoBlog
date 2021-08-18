package com.example.backend.shiro;

import com.example.backend.dto.Permission;
import com.example.backend.dto.Role;
import com.example.backend.dto.User;
import com.example.backend.mapper.RoleMapper;
import com.example.backend.service.RoleService;
import com.example.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class MyRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    /**
     * 授权
     *
     * @param principalCollection 身份集合
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        String account= user.getAccount();
        //获取用户的所有角色
        Set<Role> roles= userService.getRoleByAccount(account);
        log.warn(roles.toString());
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        for(Role role:roles){
            simpleAuthorizationInfo.addRole(role.getRoleName());
            //获取每一个角色的所有权限
            for(Permission permission:roleService.getPermissionByRoleId(role.getRoleId())){
                simpleAuthorizationInfo.addStringPermission(permission.getPermissionName());
            }
        }
        return simpleAuthorizationInfo;
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
