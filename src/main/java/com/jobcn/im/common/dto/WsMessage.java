package com.jobcn.im.common.dto;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * 消息类型
 * 用于websocket识别返回的json数据是什么类型的消息
 */

@Component
public class WsMessage {
    /**
     * 0: 通用
     * 1: 提示
     * 10：历史信息（首次加载）
     * 11: 聊天记录变动（异步推送）
     * 20: 好友列表（首次加载）
     * 21: 好友列表变动（异步推送）
     * 40： 无该用户
     */
    public final static int COMMON=0;
    public final static int NOTIFICATION=1;
    public final static int MESSAGE_LIST=10;
    public final static int MESSAGE_UPDATE=11;
    public final static int CONTACT_LIST=20;
    public final static int CONTACT_UPDATE=21;
    public final static int NO_USER=40;

    /**
     * 封装Jobcn websocket返回的json文本
     * @param type
     * @param body
     * @return
     */
    public JSONObject pack(int type,Object body){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type",type);
        jsonObject.put("body",body);
        return jsonObject;
    }
}
