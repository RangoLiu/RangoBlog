package com.example.backend.mapper;

import com.example.backend.dto.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface RoleMapper {
    @Select("select permission_id as permissionId,permission_name as permissionName from permission where role_id = #{roleId}")
    Set<Permission> getPermissionByRoleId(int roleId);
}
