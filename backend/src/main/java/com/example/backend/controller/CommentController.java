package com.example.backend.controller;

import com.example.backend.dao.Comment;
import com.example.backend.service.CommentService;
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
    List<List<Comment>> getComment(
            @RequestParam("blogId") int blogId){
        return commentService.getComment(blogId);
    }
}
