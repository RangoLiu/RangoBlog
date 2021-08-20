package com.example.backend.dto;

import lombok.Data;

@Data
public class LabelCount {
    private int labelId;
    private String labelContent;
    //包含当前label的blog的数量
    private int blogCount;
}
