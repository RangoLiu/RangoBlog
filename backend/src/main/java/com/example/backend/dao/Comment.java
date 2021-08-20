package com.example.backend.dao;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private int commentId;

    //评论组ID
    private int commentGroupId;
    private String commentText;

    //所在博客的ID
    private int blogId;
    //发送评论的账号
    private String account;
    //@对象的账号
    private String toAccount;

    private Date commentDate;
}
