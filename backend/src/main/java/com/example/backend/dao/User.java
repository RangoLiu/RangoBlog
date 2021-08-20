package com.example.backend.dao;

import lombok.Data;

@Data
public class User {
    private String username;
    private String account;
    private String password;
    private byte[] avatar;
}
