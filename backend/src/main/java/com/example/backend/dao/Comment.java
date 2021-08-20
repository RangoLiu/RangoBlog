package com.example.backend.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private int commentId;

    //评论组ID
    private int commentGroupId;
    private String commentContent;

    //所在博客的ID
    private int blogId;
    //发送评论的账号
    private String account;
    //@对象的账号
    private String toAccount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentDate;
}
