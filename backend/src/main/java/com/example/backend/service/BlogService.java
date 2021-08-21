package com.example.backend.service;

import com.example.backend.dto.BlogInfoAndData;
import com.example.backend.mapper.BlogMapper;
import com.example.backend.mapper.CommentMapper;
import com.example.backend.mapper.UserLikeBlogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
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

    public List<BlogInfoAndData> getBlogInfoAndData(int pageNo, int pageLen, int sortMethod, int ascOrDesc, int filterLabelId) {
        String sortMethodS = sortMethod == 0 ? "blog_date" : "blog_pageviews";
        String ascOrDescS = ascOrDesc == 0 ? "asc" : "desc";
        log.warn(sortMethodS+"   "+ascOrDescS);
        List<BlogInfoAndData> res;
        log.warn(filterLabelId+"");
        if (filterLabelId == -1) {
            res = blogMapper.getBlogInfoAndDataWithoutFilterLabel((pageNo - 1) * pageLen, pageLen, sortMethodS, ascOrDescS);
        } else {
            res = blogMapper.getBlogInfoAndDataWithFilterLabel((pageNo - 1) * pageLen, pageLen, sortMethodS, ascOrDescS, filterLabelId);
        }
        for(BlogInfoAndData e:res){
            Integer commentCount=commentMapper.getCommentCount(e.getBlogId());
            e.setCommentCount(commentCount==null?0:commentCount);
            Integer likeCount=userLikeBlogMapper.getLikeCount(e.getBlogId());
            e.setLikeCount(likeCount==null?0:likeCount);
        }
        return res;
    }

    public int getBlogCount(int filterLabelId) {
        if (filterLabelId == -1) {
            return blogMapper.getBlogCountWithoutFilterLabel();
        } else {
            return blogMapper.getBlogCountWithFilterLabel(filterLabelId);
        }
    }

    public boolean viewBlog(int blogId){
        return blogMapper.viewBlog(blogId)==1;
    }
}