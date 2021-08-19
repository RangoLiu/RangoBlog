package com.example.backend.mapper;

import com.example.backend.vo.UserLikeBlog;
import org.apache.ibatis.annotations.*;


@Mapper
public interface BlogMapper {
    @Insert("insert into blog (blog_content) values (#{blogContent})")
    int addBlog(String blogContent);

    @Insert("insert into user_like_blog (account,blog_id) values (#{account},#{blogId})")
    int likeBlog(String account,int blogId);

    @Delete("delete from user_like_blog where account=#{account} and blog_id=#{blogId}")
    int unlike(String account,int blogId);

    @Select("select * from user_like_blog where account=#{account} and blog_id=#{blogId}")
    UserLikeBlog checkLike(String account, int blogId);

    @Delete("delete from blog_label where blog_id=#{blogId}")
    int deleteLabel(int blogId);

    @Insert("insert into blog_label (blog_id,label_id) values (#{blogId},#{labelId})")
    int addLabel(int blogId,int labelId);
}
