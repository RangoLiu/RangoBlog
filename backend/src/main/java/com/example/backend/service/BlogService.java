package com.example.backend.service;

import com.example.backend.dto.BlogInfoAndData;
import com.example.backend.mapper.BlogMapper;
import com.example.backend.mapper.CommentMapper;
import com.example.backend.mapper.UserLikeBlogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BlogService {
    @Resource
    BlogMapper blogMapper;

    @Resource
    CommentMapper commentMapper;

    @Resource
    UserLikeBlogMapper userLikeBlogMapper;

    public int addBlog(String blogContent) {
        return blogMapper.addBlog(blogContent);
    }

    public boolean likeBlog(String account, int blogId) {
        return blogMapper.likeBlog(account, blogId) == 1;
    }

    public boolean unlikeBlog(String account, int blogId) {
        return blogMapper.unlike(account, blogId) == 1;
    }

    public boolean checkLike(String account, int blogId) {
        return blogMapper.checkLike(account, blogId) != null;
    }

    public int updateLabel(int blogId, List<Integer> labelList) {
        int deleteLabel = blogMapper.deleteLabel(blogId);
        int addLabel = 0;
        for (int label : labelList) {
            addLabel += blogMapper.addLabel(blogId, label);
        }
        return addLabel - deleteLabel;
    }

    public List<BlogInfoAndData> getBlogInfoAndData(int pageNo, int pageLen, int sortMethod, int ascOrDesc) {
        List<BlogInfoAndData> res = new ArrayList<>();
        if (sortMethod == 1) {
            //默认排序
            res = blogMapper.getBlogInfoAndDataByDefault((pageNo - 1) * pageLen, pageLen);
        } else if (sortMethod == 2) {
            //根据时间排序
            res = blogMapper.getBlogInfoAndDataByTime((pageNo - 1) * pageLen, pageLen);
        } else if (sortMethod == 3) {
            //根据浏览量排序
            res = blogMapper.getBlogInfoAndDataByPageviews((pageNo - 1) * pageLen, pageLen);
        }
        for (BlogInfoAndData blogInfoAndData : res) {
            if (blogInfoAndData != null) {
                //计算评论量
                Integer commentCount = commentMapper.getCommentCount(blogInfoAndData.getBlogId());
                blogInfoAndData.setCommentCount(commentCount == null ? 0 : commentCount);
                //计算点赞数量
                Integer likeCount = userLikeBlogMapper.getLikeCount(blogInfoAndData.getBlogId());
                blogInfoAndData.setLikeCount(likeCount == null ? 0 : likeCount);
            }
        }
        if(ascOrDesc==-1){
            Collections.reverse(res);
        }
        return res;
    }
}
