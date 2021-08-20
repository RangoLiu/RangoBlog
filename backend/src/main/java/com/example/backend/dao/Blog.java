package com.example.backend.dao;

import lombok.Data;

@Data
public class Blog {
    private int blogId;
    private String blogContent;
    private int blogLike;
}
