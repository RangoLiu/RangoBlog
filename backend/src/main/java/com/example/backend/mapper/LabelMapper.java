package com.example.backend.mapper;

import com.example.backend.dao.Label;
import com.example.backend.dto.LabelCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LabelMapper {
    @Select("select label_id as labelId,label_content as labelContent,count(*) as blogCount" +
            " from label a,blog_label b" +
            " where a.id=b.label_id" +
            " group by label_id")
    List<LabelCount> getLabelCount();

    @Select("select label_id as labelId,label_content as labelContent" +
            " from label a,blog_label b" +
            " where a.id = b.label_id and b.blog_id = #{blogId}")
    List<Label> getLabel(int blogId);
}
