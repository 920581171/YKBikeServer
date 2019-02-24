package com.yk.service;

import com.yk.pojo.ChatMessage;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ChatMessageService {
    ChatMessage searchById(String id) throws Exception;

    List<ChatMessage> searchAllChatMessage() throws Exception;

    List<ChatMessage> searchAllChatMessageByBothId(String id1,String id2) throws Exception;

    int addChatMessage(ChatMessage chatMessage) throws Exception;

    int updateChatMessage(ChatMessage chatMessage) throws Exception;

    int deleteChatMessage(ChatMessage chatMessage) throws Exception;
}
