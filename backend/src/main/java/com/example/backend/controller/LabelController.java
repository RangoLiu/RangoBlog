package com.example.backend.controller;

import com.example.backend.dto.LabelCount;
import com.example.backend.service.LabelService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/label")
public class LabelController {
    @Resource
    LabelService labelService;

    /**
     * 获取标签以及对应博客的数量
     *
     * @return 返回【标签ID，标签内容，对应博客数量】
     */
    @RequiresRoles({"visitor", "admin"})
    @RequestMapping(value = "/getLabelCount", method = RequestMethod.GET)
    @ResponseBody
    public List<LabelCount> getLabelCount() {
        return labelService.getLabelCount();
    }

}
