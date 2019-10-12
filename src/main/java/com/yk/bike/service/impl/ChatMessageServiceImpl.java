package com.yk.bike.service.impl;

import com.yk.bike.dao.impl.ChatMessageDaoImpl;
import com.yk.bike.pojo.ChatMessage;
import com.yk.bike.service.ChatMessageService;
import com.yk.bike.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private ChatMessageDaoImpl chatMessageDao;

    @Override
    public String findALLChatMessageByBothId(String id1, String id2) throws Exception {
        List<ChatMessage> chatMessages = chatMessageDao.searchAllChatMessageByBothId(id1, id2);
        return GsonUtils.responseObjectJson(chatMessages != null, chatMessages);
    }
}
