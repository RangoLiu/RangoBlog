package com.example.backend.service;

import com.example.backend.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserRoleService {
    @Resource
    UserRoleMapper userRoleMapper;

    public int registerUserRole(String account){
        return userRoleMapper.registerUserRole(account);
    }
}
