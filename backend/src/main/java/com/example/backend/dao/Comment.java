package com.example.backend.dao;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private int commentId;
    private String commentText;
    private int blogId;

    private Date commentData;
}
