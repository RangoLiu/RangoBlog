package com.example.backend.mapper;

import com.example.backend.dao.Comment;
import com.example.backend.dto.UserComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("select count(*) from comment group by blog_id having blog_id=#{blogId}")
    Integer getCommentCount(int blogId);

    @Select("select avatar,username,comment_id as commentId,comment_group_id as commentGroupId," +
            "comment_content as commentContent,blog_id as blogId,a.account as account,to_account as toAccount,comment_date as commentDate" +
            " from comment a,user b" +
            " where a.account=b.account and blog_id=#{blogId}" +
            " order by comment_group_id,comment_date")
    List<UserComment> getComment(int blogId);
}
