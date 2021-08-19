package com.example.backend.service;

import com.example.backend.mapper.BlogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BlogService {
    @Resource
    BlogMapper blogMapper;

    public int addBlog(String blogContent){
        return blogMapper.addBlog(blogContent);
    }

    public boolean likeBlog(String account,int blogId){
        return blogMapper.likeBlog(account,blogId)==1;
    }

    public boolean unlikeBlog(String account,int blogId){
        return blogMapper.unlike(account,blogId)==1;
    }

    public boolean checkLike(String account,int blogId){
        return blogMapper.checkLike(account,blogId)!=null;
    }
}
