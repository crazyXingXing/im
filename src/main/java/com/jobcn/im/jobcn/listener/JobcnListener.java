package com.jobcn.im.jobcn.listener;

import com.jobcn.im.common.factory.ChatManagerFactory;
import com.jobcn.im.common.factory.ConnectionFactory;
import com.jobcn.im.common.dto.WsMessage;
import com.jobcn.im.common.service.WebsocketService;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterListener;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.Jid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

/**
 * openfire 事件监听器
 */
@Component
public class JobcnListener {

    public final static String DISTINATION="/queue/notifications";

    @Autowired
    private ChatManagerFactory chatManagerFactory;
    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private WebsocketService websocketService;

    @Autowired
    private WsMessage jobcnWsPacker;

    /**
     * 初始化注册监听器
     * @param user
     */
    public void initListener(String user){
        registerChatListener(user);
        registerRosterListener(user);
    }

    /**
     * 监听信息变化
     */
    public  void registerChatListener(String user) {
        ChatManager manager = chatManagerFactory.getChatManager(user);
        manager.addIncomingListener(new IncomingChatMessageListener() {
            @Override
            public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
                LocalDate today = LocalDate.now();
                LocalTime specificSecondTime = LocalTime.now();
                String payload = "["+today+" "+specificSecondTime+"] "+message.toString() + ": "+ message.getBody();
                websocketService.send(user,DISTINATION,jobcnWsPacker.pack(WsMessage.MESSAGE_UPDATE,payload));
            }
        });
    }

    /**
     * 监听联系人变化
     */
    public void registerRosterListener(String user) {
        Roster roster = Roster.getInstanceFor(connectionFactory.getConnection(user));
        roster.addRosterListener(new RosterListener() {
            @Override
            public void entriesAdded(Collection<Jid> addresses) {
                //TODO
                System.out.println("entriesAdded");
            }

            @Override
            public void entriesUpdated(Collection<Jid> addresses) {
                //TODO
                System.out.println("entriesUpdated");
            }

            @Override
            public void entriesDeleted(Collection<Jid> addresses) {
                //TODO
                System.out.println("entriesDeleted");
            }

            @Override
            public void presenceChanged(Presence presence) {
                //TODO
                System.out.println("Presence changed: " + presence.getFrom() + " " + presence);
            }
        });
    }
}
