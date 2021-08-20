package com.example.backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentMapper {
    @Select("select count(*) from comment group by blog_id having blog_id=#{blogId}")
    Integer getCommentCount(int blogId);
}
