package com.jobcn.im.jobcn.controller;

import com.jobcn.im.common.dto.ResponseResult;
import com.jobcn.im.common.entity.OfMessageArchive;
import com.jobcn.im.jobcn.service.JobcnImService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * RESTFul API
 */
@RestController
public class ImRestController {

    @Autowired
    private JobcnImService jobcnImService;

    /**
     * 获取联系人
     * @param principal
     */
    @RequestMapping(value = "getRoster")
    public ResponseResult getRoster(Principal principal){
        List rosterList = jobcnImService.getRoster(principal.getName());
        return ResponseResult.success("请求成功", rosterList);
    }

    /**
     * 获取信息档案(所有历史聊天记录)
     * @param principal
     */
    @RequestMapping(value = "getMessageArchive")
    public ResponseResult getMessageArchive(Principal principal){
        List<OfMessageArchive> list = jobcnImService.getMessageArchive(principal.getName());
        return ResponseResult.success("请求成功",list);
    }


    /**
     * 删除联系人
     * @param principal
     * @param roster
     */
    @RequestMapping("delRoster")
    public ResponseResult delRoster(Principal principal,String roster){
        //TODO
        return ResponseResult.success("请求成功",null);
    }


    /**
     * 添加联系人
     * @param principal
     * @param roster
     */
    @RequestMapping("addRoster")
    public ResponseResult addRoster(Principal principal,String roster){
        //TODO
        return ResponseResult.success("请求成功",null);
    }

    /**
     * 下线
     * @param principal
     * @return
     */
    @RequestMapping("offline")
    public ResponseResult offline(Principal principal){
        //TODO
        return ResponseResult.success("请求成功",null);
    }
    /**
     * 获取未读信息数量
     * @param principal
     */
    @RequestMapping("getUnreadCount")
    public ResponseResult getUnreadCount(Principal principal){
        //TODO
        return ResponseResult.success("请求成功",null);
    }

    /**
     * 获取未读信息数量(指定联系人)
     * @param principal
     */
    @RequestMapping("getUnreadCountByRoster")
    public ResponseResult getUnreadCountByRoster(Principal principal,String roster){
        //TODO
        return ResponseResult.success("请求成功",null);
    }

    /**
     * 设置某联系人的所有信息已读
     * @param principal
     */
    @RequestMapping("read")
    public ResponseResult read(Principal principal,String roster){
        //TODO
        return ResponseResult.success("请求成功",null);
    }

}
