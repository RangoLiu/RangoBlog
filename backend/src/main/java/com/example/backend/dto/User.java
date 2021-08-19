package com.example.backend.dto;

import lombok.Data;

@Data
public class User {
    private String username;
    private String account;
    private String password;
    private byte[] avatar;
}
