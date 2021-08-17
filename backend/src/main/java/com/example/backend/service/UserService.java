package com.example.backend.service;

import com.example.backend.dto.User;
import com.example.backend.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    public void register(MultipartFile avatar, String username, String account, String password) throws IOException, SQLException {
        Blob avatarBlob=new SerialBlob(avatar.getBytes());
        userMapper.register(avatarBlob,username,account,password);
    }
    public User login(String account){
        return userMapper.login(account);
    }
}
