package com.example.backend.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Blog {
    private int blogId;
    private String blogContent;
    private int blogLike;
}
