package com.jobcn.im.jobcn.controller;

import com.jobcn.im.common.dto.ResponseResult;
import com.jobcn.im.common.dto.SendMesssageDto;
import com.jobcn.im.jobcn.service.JobcnImService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Websocket 的 API 接口
 * js调用方式：stomp.send("/chat", {}, JSON.stringify({'toUser': toUser, 'message': message}));
 */
@Controller
public class ImWsController {

    @Autowired
    private JobcnImService jobcnImService;

    /**
     * 上线
     * @param principal
     * @return
     */
    @MessageMapping("online")
    public ResponseResult online(Principal principal){
        jobcnImService.online(principal.getName());
        return ResponseResult.success("请求成功",null);
    }

    /**
     * 发送信息
     * @param principal
     * @param messageSource
     */
    @MessageMapping("send")
    public ResponseResult send(Principal principal, SendMesssageDto messageSource ){
        jobcnImService.sendMessage(principal.getName(),messageSource.getToUser(),messageSource.getMessage());
        return ResponseResult.success("发送信息成功",null);
    }
}