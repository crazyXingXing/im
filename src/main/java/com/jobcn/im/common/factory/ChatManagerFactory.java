package com.jobcn.im.common.factory;

import com.jobcn.im.jobcn.listener.JobcnListener;
import org.jivesoftware.smack.chat2.ChatManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;


/**
 * ChatManager 工厂
 */
@Component
public class ChatManagerFactory {

    public static ConcurrentHashMap<String, ChatManager> chatManagerMap = new ConcurrentHashMap();

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private JobcnListener chatListenHandle;

    /**
     * 获取openfire对话管理器
     * @param user
     * @return
     */
    public ChatManager getChatManager(String user) {
        ChatManager chatManager = chatManagerMap.get(user);
        if (chatManager==null) {
            chatManager = ChatManager.getInstanceFor(connectionFactory.getConnection(user));
            //初始化注册监听器
            chatManagerMap.put(user, chatManager);
            chatListenHandle.initListener(user);
            return chatManager;
        }
        return chatManager;
    }

}
