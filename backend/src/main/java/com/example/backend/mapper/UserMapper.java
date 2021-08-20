package com.example.backend.mapper;

import com.example.backend.dao.Role;
import com.example.backend.dao.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Blob;
import java.util.Set;

@Mapper
public interface UserMapper {
    @Insert("insert into user (avatar,username,account,password) values (#{avatar},#{username},#{account},#{password})")
    void register(Blob avatar, String username, String account, String password);

    @Select("select * from user where account=#{account}")
    User login(String account);

    @Select("select c.role_id as roleId,c.role_name as roleName from user a,user_role b,role c where a.account = #{account} and b.user_account = #{account} and b.role_id = c.role_id")
    Set<Role> getRoleByAccount(String account);

    @Update("update user set password=#{newPassword} where account=#{account} and password=#{oldPassword}")
    int updatePassword(String account, String oldPassword, String newPassword);

    @Update("update user set avatar=#{avatar},username=#{userName} where account=#{account}")
    int updateAvatar(String account, String userName, Blob avatar);
}