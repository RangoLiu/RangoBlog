package com.example.backend.mapper;

import com.example.backend.dto.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper {
    @Select("select * from admin_info where account='123456'")
    Admin getAdminInfo(String account);

    @Update("update admin_info set edu=#{edu},signature=#{signature},email=#{email},link=#{link} where account=#{account}")
    int updateInfo(String account,String edu,String signature,String email,String link);
}
