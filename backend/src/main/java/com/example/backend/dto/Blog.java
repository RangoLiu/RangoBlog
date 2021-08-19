package com.example.backend.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Blog {
    private String blogContent;
    private int like;
    @DateTimeFormat(pattern = "yyy-MM-dd HH-mm-ss")
    private Date date;
}
