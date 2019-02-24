package com.yk.impl;

import com.yk.dao.ChatRoomDao;
import com.yk.pojo.ChatRoom;
import com.yk.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yk.Utils.RandomUtils.randomId;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
    @Autowired
    ChatRoomDao chatRoomDao;

    @Override
    public ChatRoom searchById(String id) throws Exception {
        return chatRoomDao.selectOne(ChatRoomDao.COLUMN_ID, id);
    }

    @Override
    public ChatRoom searchBothAndAdd(String fristId, String secondId) throws Exception {
        ChatRoom chatRoom = chatRoomDao.selectBoth(fristId, secondId);
        if (chatRoom == null) {
            chatRoom = new ChatRoom().setFristId(fristId).setSecondId(secondId);
            addChatRoom(chatRoom);
        }
        return chatRoom;
    }

    @Override
    public List<ChatRoom> searchByFristId(String fristId) throws Exception {
        return chatRoomDao.selectList(ChatRoomDao.COLUMN_FRIST_ID, fristId);
    }

    @Override
    public List<ChatRoom> searchBySecondId(String secondId) throws Exception {
        return chatRoomDao.selectList(ChatRoomDao.COLUMN_SECOND_ID, secondId);
    }

    @Override
    public List<ChatRoom> searchAllChatRoom() throws Exception {
        return chatRoomDao.selectTable();
    }

    @Override
    public int addChatRoom(ChatRoom chatRoom) throws Exception {
        String roomId = randomId("ROOM");
        while (chatRoomDao.selectOne(ChatRoomDao.COLUMN_ROOM_ID, roomId) != null) {
            roomId = randomId("ROOM");
        }

        chatRoom.setRoomId(roomId);
        chatRoomDao.insert(chatRoom);

        return chatRoom.getId();
    }

    @Override
    public int updateChatRoom(ChatRoom chatRoom) throws Exception {
        return chatRoomDao.update(chatRoom);
    }

    @Override
    public int deleteChatRoom(ChatRoom chatRoom) throws Exception {
        return chatRoomDao.delete(chatRoom);
    }
}
