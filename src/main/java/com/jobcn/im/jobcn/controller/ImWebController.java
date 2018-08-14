package com.jobcn.im.jobcn.controller;

import com.jobcn.im.jobcn.service.JobcnImService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转Controller
 */
@Controller
public class ImWebController {

    @Autowired
    JobcnImService jobcnImService;

    /**
     * Page-登录
     * @return
     */
    @RequestMapping("login")
    public String login(){
        return "login";
    }

    /**
     * Page-聊天室
     * @return
     */
    @RequestMapping("chat")
    public String chat(){
        return "chat";
    }

}
