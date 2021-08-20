package com.example.backend.mapper;

import com.example.backend.dao.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("select count(*) from comment group by blog_id having blog_id=#{blogId}")
    Integer getCommentCount(int blogId);

    @Select("select comment_id as commentId,comment_group_id as commentGroupId," +
            "comment_content as commentContent,blog_id as blogId,account as account,to_account as toAccount,comment_date as commentDate" +
            " from comment" +
            " where blog_id=#{blogId}" +
            " order by comment_group_id,comment_date")
    List<Comment> getComment(int blogId);
}
