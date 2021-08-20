package com.example.backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserLikeBlogMapper {
    @Select("select count(*)" +
            " from user_like_blog" +
            " group by blog_id" +
            " having blog_id=#{blogId}")
    Integer getLikeCount(int blogId);
}
