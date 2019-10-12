package com.yk.bike.dao.impl;

import com.yk.bike.mapper.ChatMessageMapper;
import com.yk.bike.pojo.ChatMessage;
import com.yk.bike.dao.ChatMessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yk.bike.utils.RandomUtils.randomId;

@Service
public class ChatMessageDaoImpl implements ChatMessageDao {
    @Autowired
    ChatMessageMapper chatMessageDao;


    @Override
    public ChatMessage searchById(String id) throws Exception {
        return chatMessageDao.selectOne(ChatMessageMapper.COLUMN_ID, id);
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
        while (chatMessageDao.selectOne(ChatMessageMapper.COLUMN_CHAT_ID, chatId) != null) {
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
