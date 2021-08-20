package com.example.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserComment {
    private byte[] avatar;
    private String username;

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
