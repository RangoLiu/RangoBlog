package com.example.backend.controller;

import com.example.backend.dto.User;
import com.example.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.Security;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class UserController {
    @Resource
    UserService userService;

    /**
     * 实现注册功能
     *
     * @param avatar 头像
     * @param username 用户名
     * @param account 账号
     * @param password 密码
     * @throws SQLException insert可能抛出的异常
     * @throws IOException 字节数组转Blob抛出的异常
     */
    @RequestMapping(value="/register",method = RequestMethod.POST)
    @ResponseBody
    public void register(
            @RequestParam("avatar") MultipartFile avatar,
            @RequestParam("username") String username,
            @RequestParam("account") String account,
            @RequestParam("password") String password) throws SQLException, IOException {
        userService.register(avatar,username,account,password);
    }

    /**
     * 实现登录功能
     *
     * @param account 账号
     * @param password 密码
     * @return 成功登录返回1，账号不存在返回2，密码错误返回3，没有权限返回4
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public int login(
            @RequestParam("account") String account,
            @RequestParam("password") String password){
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(account,password);
        try{
            subject.login(usernamePasswordToken);
        }
        catch (UnknownAccountException e){
            log.warn("账号不存在");
            return 2;
        }
        catch (AuthenticationException e){
            log.warn("密码错误");
            return 3;
        }
        catch (AuthorizationException e){
            log.warn("没有权限");
            return 4;
        }
        return 1;
    }
}
