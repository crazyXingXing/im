package com.jobcn.im.common.factory;

import com.jobcn.im.common.config.OpenFireConfig;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * openfire 连接管理
 */
@Component
public class ConnectionFactory {

    @Autowired
    OpenFireConfig openFire;

    public static ConcurrentHashMap<String, XMPPTCPConnection> connectionMap = new ConcurrentHashMap();


    /**
     * 根据用户openfire服务器的连接
     * @param user
     * @return
     */
    public XMPPTCPConnection getConnection(String user){
        XMPPTCPConnection connection=connectionMap.get(user);
        if (connection==null){
            connection = buildConnection();
            login(connection,user);
            connectionMap.put(user,connection);
        }
        return connection;
    }

    /**
     * 获得与openfire服务器的连接
     *
     * @return
     */
    public XMPPTCPConnection buildConnection() {
        XMPPTCPConnection connection = null;
        try {
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    .setHost(openFire.getHost())
                    .setXmppDomain(openFire.getDomain())
                    .setPort(openFire.getPort())
                    //设置不在线，用于获取离线信息
                    .setSendPresence(true)
                    //是否开启安全模式
                    .setSecurityMode(XMPPTCPConnectionConfiguration.SecurityMode.disabled)
                    //是否开启压缩
                    .setCompressionEnabled(false)
                    //开启调试模式
                    .setDebuggerEnabled(false).build();
            connection = new XMPPTCPConnection(config);
            //连接
            connection.connect();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 登录openfire服务器
     * @param connection
     * @param user
     * @return
     */
    public XMPPTCPConnection login(XMPPTCPConnection connection,String user){
        try {
            connection.login(user, openFire.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //注册事件监听器
        return connection;
    }
}
