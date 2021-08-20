package com.example.backend.service;

import com.example.backend.dao.Comment;
import com.example.backend.mapper.CommentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CommentService {
    @Resource
    CommentMapper commentMapper;

    public List<List<Comment>> getComment(int blogId){
        List<Comment> allComment= commentMapper.getComment(blogId);
        log.warn(allComment.toString());
        List<List<Comment>> res=new ArrayList<>();
        if(allComment.isEmpty()){
            return res;
        }
        List<Comment> group=new ArrayList<>();
        group.add(allComment.get(0));
        for(int i=1;i<allComment.size();i++){
            if(allComment.get(i).getCommentGroupId()!=allComment.get(i-1).getCommentGroupId()){
                res.add(group);
                group=new ArrayList<>();
            }
            group.add(allComment.get(i));
        }
        return res;
    }
}
