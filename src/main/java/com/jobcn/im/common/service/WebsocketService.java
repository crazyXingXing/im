package com.jobcn.im.common.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


/**
 * Websocket API
 */
@Service
public class WebsocketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * websocket 推送消息(To single user)
     * @param message
     */
    public void send(String toUser,String destination,JSONObject message) {
        messagingTemplate.convertAndSendToUser(toUser, destination, message);
    }
}
