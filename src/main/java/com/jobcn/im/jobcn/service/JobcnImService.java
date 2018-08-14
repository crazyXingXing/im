package com.jobcn.im.jobcn.service;

import com.alibaba.fastjson.JSONObject;
import com.jobcn.im.common.config.OpenFireConfig;
import com.jobcn.im.common.entity.OfMessageArchive;
import com.jobcn.im.common.repository.OfMessageArchiveRepository;
import com.jobcn.im.common.service.OpenfireService;
import com.jobcn.im.common.service.WebsocketService;
import com.jobcn.im.common.dto.WsMessage;
import org.jivesoftware.smack.roster.RosterEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jobcn IM API
 */
@Controller
public class JobcnImService {

    public final static String JOBCN_GROUP = "jobcn-dialogue";

    @Autowired
    OpenFireConfig openFireConfig;

    @Autowired
    OpenfireService openfireService;

    @Autowired
    OfMessageArchiveRepository ofMessageArchiveRepository;

    @Autowired
    WebsocketService websocketService;

    @Autowired
    WsMessage wsMessage;

    /**
     * 上线
     *
     * @param user
     */
    public void online(String user) {
        openfireService.online(user);
    }

    /**
     * 发送信息
     *
     * @param fromUser
     * @param toUser
     * @param message
     */
    public void sendMessage(String fromUser, String toUser, String message) {
        JSONObject jsonObject = wsMessage.pack(WsMessage.MESSAGE_UPDATE, message);
        openfireService.sendMessage(fromUser, toUser, jsonObject.toJSONString());
    }


    /**
     * 获取信息档案(所有历史聊天记录)
     * @param user
     */
    public List<OfMessageArchive> getMessageArchive(String user){
        user= user+openFireConfig.getDomain();
        return ofMessageArchiveRepository.findByFromJIDOrAndToJIDOrderBySentDate(user,user);
    }

    /**
     * 获取联系人
     *
     * @param user
     * @return
     */
    public  List<Map<String,String>> getRoster(String user) {
        List<Map<String,String>> list = new ArrayList<>();
        List<RosterEntry> rosterEntries = openfireService.getRosterEntry(user, JOBCN_GROUP);
        for (RosterEntry rosterEntry:rosterEntries) {
            Map<String,String> map =new HashMap<>();
            String name = rosterEntry.getName();
            String str = rosterEntry.toString();
            map.put(name,str);
            list.add(map);
        }
        return list;
    }

}
