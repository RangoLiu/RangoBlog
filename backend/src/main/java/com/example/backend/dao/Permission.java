package com.example.backend.dao;

import lombok.Data;

@Data
public class Permission {
    private int permissionId;
    private String permissionName;
    private int roleId;
}
