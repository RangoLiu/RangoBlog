package com.example.backend.controller;

import com.example.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@Slf4j
public class UserController {
    @Resource
    UserService userService;

    /**
     * 实现密码加密
     *
     * @param account 账号
     * @param password 密码
     * @return md5加密后的密码
     */
    String getMd5Password(String account,String password){
        //盐，通过账号得到
        ByteSource salt=ByteSource.Util.bytes(account);
        Object md5Password= new SimpleHash("md5",password,salt);
        return md5Password.toString();
    }

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
        String md5Password=getMd5Password(account,password);
        userService.register(avatar,username,account,md5Password);
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
        String md5Password=getMd5Password(account,password);
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(account,md5Password);
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
        if(subject.isAuthenticated()){
            log.warn("成功登录");
        }
        Session session=subject.getSession();
        session.setAttribute("account",account);
        return 1;
    }

    @RequiresRoles("admin")
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    @ResponseBody
    public void admin(){
        Session session = SecurityUtils.getSubject().getSession();
        log.warn(session.getAttribute("account").toString());
    }

    @RequestMapping(value = "/unauthorized")
    @ResponseBody
    public void unauthorized(){
        log.warn("没有权限");
    }
}
