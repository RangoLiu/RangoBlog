package com.example.backend.service;

import com.example.backend.mapper.AdminMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Service
public class AdminService {
    @Resource
    AdminMapper adminMapper;

    public int updateInfo(String account,String edu,String signature,String email,String link){
        return adminMapper.updateInfo(account,edu,signature,email,link);
    }
}
