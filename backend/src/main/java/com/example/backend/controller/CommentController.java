package com.example.backend.controller;

import com.example.backend.dto.UserComment;
import com.example.backend.service.CommentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "comment")
public class CommentController {
    @Resource
    CommentService commentService;

    @RequiresRoles({"visitor","admin"})
    @RequestMapping(value = "getComment",method = RequestMethod.GET)
    @ResponseBody
    List<List<UserComment>> getComment(
            @RequestParam("blogId") int blogId){
        return commentService.getComment(blogId);
    }

    /**
     * 评论或者回复
     *
     * @param commentGroupId 评论组ID
     * @param commentContent 评论内容
     * @param blogId 博客ID
     * @param toAccount @用户ID
     * @return 是否成功评论或者回复
     */
    @RequiresRoles({"visitor","admin"})
    @RequestMapping(value = "/addComment",method = RequestMethod.POST)
    @ResponseBody
    boolean addComment(
            @RequestParam(value = "commentGroupId",defaultValue = "-1") long commentGroupId,
            @RequestParam("commentContent") String commentContent,
            @RequestParam("blogId") int blogId,
            @RequestParam(value = "toAccount",defaultValue = "123456") String toAccount){
        String account= SecurityUtils.getSubject().getSession().getAttribute("account").toString();
        return commentService.addComment(commentGroupId, commentContent, blogId, account, toAccount)==1;
    }
}
