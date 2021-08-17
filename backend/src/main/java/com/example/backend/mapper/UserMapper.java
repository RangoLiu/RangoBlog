package com.example.backend.mapper;

import com.example.backend.dto.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Blob;

@Mapper
public interface UserMapper {
    @Insert("insert into user (avatar,username,account,password) values (#{avatar},#{username},#{account},#{password})")
    void register(Blob avatar,String username,String account,String password);

    @Select("select * from user where account=#{account}")
    User login(String account);
}
