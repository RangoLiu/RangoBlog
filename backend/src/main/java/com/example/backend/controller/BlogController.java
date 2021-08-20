package com.example.backend.controller;

import com.example.backend.dto.BlogInfoAndData;
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
import java.util.ArrayList;
import java.util.List;

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
    @RequiresRoles("visitor")
    @RequestMapping(value = "checkLike" ,method = RequestMethod.POST)
    @ResponseBody
    public boolean checkLike(
            @RequestParam("blogId") int blogId){
        String account=getCurAccount();
        log.warn(account+"  "+blogId);
        return blogService.checkLike(account,blogId);
    }

    /**
     * 更新当前博客的标签
     *
     * @param blogId 博客ID
     * @param labelList 标签列表
     * @return 增加的标签数量，可正可负
     */
    @RequiresRoles("admin")
    @RequestMapping(value = "/updateLabel",method = RequestMethod.POST)
    @ResponseBody
    public int updateLabel(
            @RequestParam("blogId") int blogId,
            @RequestParam("labelList") List<Integer> labelList){
        return blogService.updateLabel(blogId,labelList);
    }


    /**
     * 获取主页中博客的概述信息
     *
     * @param pageNo 页码
     * @param pageLen 页长度
     * @param sortMethod 排序方式
     * @param ascOrDesc 升序或者降序
     * @return 博客概述信息列表
     */
    @RequiresRoles({"visitor", "admin"})
    @RequestMapping(value = "/getBlogInfoAndData",method = RequestMethod.POST)
    @ResponseBody
    List<BlogInfoAndData> getBlogInfoAndData(
            @RequestParam("pageNo") int pageNo,
            @RequestParam("pageLen") int pageLen,
            @RequestParam(value = "sortMethod", defaultValue = "0") int sortMethod,
            @RequestParam("ascOrDesc") int ascOrDesc) {
        return blogService.getBlogInfoAndData(pageNo, pageLen, sortMethod,ascOrDesc);
    }
}
