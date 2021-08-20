package com.example.backend.service;

import com.example.backend.dto.LabelCount;
import com.example.backend.mapper.LabelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LabelService {
    @Resource
    LabelMapper labelMapper;

    public List<LabelCount> getLabelCount(){
        return labelMapper.getLabelCount();
    }
}
