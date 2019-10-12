package com.yk.bike.service.impl;

import com.yk.bike.dao.impl.ChatRoomDaoImpl;
import com.yk.bike.pojo.ChatRoom;
import com.yk.bike.service.ChatRoomService;
import com.yk.bike.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private ChatRoomDaoImpl chatRoomDao;

    @Override
    public String findALLChatRoomByFristId(String fristId) throws Exception {
        List<ChatRoom> chatRooms = chatRoomDao.searchByFristId(fristId);
        return GsonUtils.responseObjectJson(chatRooms != null, chatRooms);
    }
}
