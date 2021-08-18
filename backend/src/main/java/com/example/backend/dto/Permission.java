package com.example.backend.dto;

import lombok.Data;

@Data
public class Permission {
    private int permissionId;
    private String permissionName;
    private int roleId;
}
