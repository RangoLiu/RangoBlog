package com.example.backend.controller;

import com.example.backend.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping(value="/register",method = RequestMethod.POST)
    @ResponseBody
    public void register(
            @RequestParam("avatar") MultipartFile avatar,
            @RequestParam("username") String username,
            @RequestParam("account") String account,
            @RequestParam("password") String password) throws SQLException, IOException {
        userService.register(avatar,username,account,password);
    }
}
