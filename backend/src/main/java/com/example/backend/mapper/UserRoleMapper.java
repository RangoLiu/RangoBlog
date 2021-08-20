package com.example.backend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {
    @Insert("insert into user_role (user_account) values (#{account})")
    int registerUserRole(String account);
}
