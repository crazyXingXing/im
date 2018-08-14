package com.jobcn.im.common.service;

import com.jobcn.im.common.config.OpenFireConfig;
import com.jobcn.im.common.factory.ChatManagerFactory;
import com.jobcn.im.common.factory.ConnectionFactory;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterGroup;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jxmpp.jid.BareJid;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.jid.parts.Localpart;
import org.jxmpp.stringprep.XmppStringprepException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Openfire API
 */
@Service
public class OpenfireService {

   @Autowired
   OpenFireConfig openFireConfig;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private ChatManagerFactory chatManagerFactory;

    /**
     * 上线
     * @param user
     */
    public void online(String user) {
        chatManagerFactory.getChatManager(user);
        updatePresence(user,Presence.Type.available,Presence.Mode.chat);
    }

    /**
     * 下线 //TODO 下线功能有问题
     * @param user
     */
    public void offline(String user) {
        updatePresence(user,Presence.Type.unavailable,Presence.Mode.away);
    }

    /**
     * 更新用户状态
     * @param user
     * @param type
     * @param mode
     */
    public void updatePresence(String user,Presence.Type type,Presence.Mode mode) {
        XMPPTCPConnection connection = connectionFactory.getConnection(user);
        Presence presence = new Presence(type);
        presence.setMode(mode);
        try {
            connection.sendStanza(presence);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 向指定用户发送消息
     *
     * @param toUser
     * @param message
     */
    public void sendMessage(String fromUser, String toUser, String message) {
        ChatManager chatManager = chatManagerFactory.getChatManager(fromUser);
        Jid jid = null;
        try {
            jid = JidCreate.from(toUser + "@"+openFireConfig.getDomain());
        } catch (XmppStringprepException e) {
            e.printStackTrace();
        }
        Chat chat = chatManager.chatWith(jid.asEntityBareJidIfPossible());
        Message m = new Message();
        m.setFrom(jid);
        m.setBody(message);
        m.setType(Message.Type.chat);
        m.setTo(jid);
        try {
            chat.send(m);
        } catch (SmackException.NotConnectedException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取联系人组(所有)
     * @param user
     */
    public Collection<RosterGroup> getRoster(String user) {
        Roster roster = Roster.getInstanceFor(connectionFactory.getConnection(user));
        Collection<RosterGroup> collection =  roster.getGroups();
        return collection;
    }

    /**
     * 获取联系人组(根据组名)
     * @param user
     * @param group
     * @return
     */
    public RosterGroup getRoster(String user,String group){
        Roster roster = Roster.getInstanceFor(connectionFactory.getConnection(user));
        return roster.getGroup(group);
    }

    /**
     * 获取联系人(根据组名)
     * @param user
     * @param group
     * @return
     */
    public List<RosterEntry> getRosterEntry(String user,String group){
        Roster roster = Roster.getInstanceFor(connectionFactory.getConnection(user));
        RosterGroup rosterGroup = roster.getGroup(group);
        return rosterGroup.getEntries();
    }

    /**
     * 添加联系人 TODO 未测
     */
    public RosterEntry addRoster(String user, String addRoster,String group){
        Roster roster = Roster.getInstanceFor(connectionFactory.getConnection(user));
        BareJid bareJid = null;
        try {
            bareJid = JidCreate.bareFrom(addRoster+"@"+openFireConfig.getDomain());
            roster.createEntry(bareJid,addRoster,new String[]{group});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roster.getEntry(bareJid);
    }

    /**
     * 删除联系人 TODO 未测
     */
    public boolean delRoster(String user, String addRoster){
        Roster roster = Roster.getInstanceFor(connectionFactory.getConnection(user));
        BareJid bareJid = null;
        try {
            bareJid = JidCreate.bareFrom(addRoster+"@"+openFireConfig.getDomain());
            roster.removeEntry(roster.getEntry(bareJid));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bareJid!=null?true:false;
    }

    /**
     * TODO 注册用户
     * @param user
     */
    public void register(String user,String password){
        AccountManager accountManager = AccountManager.getInstance(connectionFactory.getConnection(user));
        try {
            accountManager.createAccount(Localpart.from(user),password);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }


}
