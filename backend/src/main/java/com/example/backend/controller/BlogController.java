package com.example.backend.controller;

import com.example.backend.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@Slf4j
@RequestMapping(value = "/blog")
public class BlogController {
    @Resource
    BlogService blogService;

    /**
     * 获取当前用户的账号
     *
     * @return 当前用户的账号
     */
    String getCurAccount(){
        return SecurityUtils.getSubject().getSession().getAttribute("account").toString();
    }

    @RequiresRoles("admin")
    @RequestMapping(value = "/addBlog",method = RequestMethod.POST)
    @ResponseBody
    public int addBlog(
            @RequestParam("blogContent") String blogContent){
        return blogService.addBlog(blogContent);
    }

    /**
     * 点赞当前查看的博客
     *
     * @param blogId 博客ID
     * @return 成功点赞返回true，否则返回false
     */
    @RequiresRoles("visitor")
    @RequestMapping(value = "/likeBlog" ,method = RequestMethod.POST)
    @ResponseBody
    public boolean likeBlog(
            @RequestParam("blogId") int blogId){
        String account=getCurAccount();
        return blogService.likeBlog(account,blogId);
    }

    /**
     * 取消点赞当前博客
     *
     * @param blogId 博客ID
     * @return 取消点赞成功返回true，否则返回false
     */
    @RequiresRoles("visitor")
    @RequestMapping(value = "/unlikeBlog" ,method = RequestMethod.DELETE)
    @ResponseBody
    public boolean unlikeBlog(
            @RequestParam("blogId") int blogId){
        String account=getCurAccount();
        return blogService.unlikeBlog(account,blogId);
    }

    /**
     * 判断当前用户是否点赞正在查看的博客
     *
     * @param blogId 博客的ID
     * @return 已经点赞则返回true，否则返回false
     */
    @RequestMapping(value = "checkLike" ,method = RequestMethod.POST)
    @ResponseBody
    public boolean checkLike(
            @RequestParam("blogId") int blogId){
        String account=getCurAccount();
        log.warn(account+"  "+blogId);
        return blogService.checkLike(account,blogId);
    }
}
