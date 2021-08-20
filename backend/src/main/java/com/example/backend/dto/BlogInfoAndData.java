package com.example.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BlogInfoAndData {
    private int blogId;
    private String blogTitle;
    private String briefContent;
    private int blogPageviews;
    private int commentCount;
    private int likeCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastChangeDate;
}
