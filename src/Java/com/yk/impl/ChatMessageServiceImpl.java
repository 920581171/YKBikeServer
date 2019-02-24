package com.yk.impl;

import com.yk.dao.ChatMessageDao;
import com.yk.pojo.ChatMessage;
import com.yk.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yk.Utils.RandomUtils.randomId;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {
    @Autowired
    ChatMessageDao chatMessageDao;


    @Override
    public ChatMessage searchById(String id) throws Exception {
        return chatMessageDao.selectOne(ChatMessageDao.COLUMN_ID, id);
    }

    @Override
    public List<ChatMessage> searchAllChatMessage() throws Exception {
        return chatMessageDao.selectTable();
    }

    @Override
    public List<ChatMessage> searchAllChatMessageByBothId(String id1, String id2) throws Exception {
        return chatMessageDao.selectBoth(id1,id2);
    }

    @Override
    public int addChatMessage(ChatMessage chatMessage) throws Exception {
        String chatId = randomId("");
        while (chatMessageDao.selectOne(ChatMessageDao.COLUMN_CHAT_ID, chatId) != null) {
            chatId = randomId("");
        }

        chatMessage.setChatId(chatId);
        chatMessageDao.insert(chatMessage);

        return chatMessage.getId();
    }

    @Override
    public int updateChatMessage(ChatMessage chatMessage) throws Exception {
        return chatMessageDao.update(chatMessage);
    }

    @Override
    public int deleteChatMessage(ChatMessage chatMessage) throws Exception {
        return chatMessageDao.delete(chatMessage);
    }
}
