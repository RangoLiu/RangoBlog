package com.example.backend.mapper;

import com.example.backend.dto.BlogInfoAndData;
import com.example.backend.dto.UserLikeBlog;
import org.apache.ibatis.annotations.*;

import java.util.List;


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

    @Select("select blog_id as blogId,blog_title as blogTitle,blog_content as blogContent,blog_pageviews as blogPageviews,blog_date as lastChangeDate" +
            " from blog" +
            " order by ${sortMethod} ${ascOrDesc}" +
            " limit #{offset},#{pageLen}")
    List<BlogInfoAndData> getBlogInfoAndDataWithoutFilterLabel(int offset, int pageLen, String sortMethod, String ascOrDesc);


    @Select("select a.blog_id as blogId,a.blog_title as blogTitle,a.blog_content as blogContent,a.blog_pageviews as blogPageviews,a.blog_date as lastChangeDate" +
            " from blog a,blog_label b" +
            " where a.blog_id = b.blog_id and b.label_id = #{filterLabelId}" +
            " order by ${sortMethod} ${ascOrDesc}" +
            " limit #{offset},#{pageLen}")
    List<BlogInfoAndData> getBlogInfoAndDataWithFilterLabel(int offset, int pageLen, String sortMethod, String ascOrDesc,int filterLabelId);

    @Select("select count(*)" +
            " from blog a,blog_label b" +
            " where a.blog_id = b.blog_id and b.label_id = #{filterLabelId}")
    int getBlogCountWithFilterLabel(int filterLabelId);

    @Select("select count(*)" +
            " from blog")
    int getBlogCountWithoutFilterLabel();

    @Update("update blog" +
            " set blog_pageviews=blog_pageviews+1" +
            " where blog_id=#{blogId}")
    int viewBlog(int blogId);
}
