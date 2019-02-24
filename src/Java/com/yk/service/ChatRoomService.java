package com.yk.service;

import com.yk.pojo.ChatRoom;

import java.util.List;

public interface ChatRoomService {
    ChatRoom searchById(String id) throws Exception;

    ChatRoom searchBothAndAdd(String fristId,String secondId) throws Exception;

    List<ChatRoom> searchByFristId(String fristId) throws Exception;

    List<ChatRoom> searchBySecondId(String secondId) throws Exception;

    List<ChatRoom> searchAllChatRoom() throws Exception;

    int addChatRoom(ChatRoom chatRoom) throws Exception;

    int updateChatRoom(ChatRoom chatRoom) throws Exception;

    int deleteChatRoom(ChatRoom chatRoom) throws Exception;
}
