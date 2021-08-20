package com.example.backend.service;

import com.example.backend.dao.Permission;
import com.example.backend.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class RoleService {
    @Resource
    RoleMapper roleMapper;

    public Set<Permission> getPermissionByRoleId(int roleId){
        return roleMapper.getPermissionByRoleId(roleId);
    }
}
