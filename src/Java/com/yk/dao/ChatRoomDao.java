package com.yk.dao;

import com.yk.pojo.ChatRoom;
import org.apache.ibatis.annotations.Param;

public interface ChatRoomDao extends BaseDao<ChatRoom> {
    String COLUMN_ROOM_ID = "room_id";
    String COLUMN_FRIST_ID = "frist_id";
    String COLUMN_SECOND_ID = "second_id";

    ChatRoom selectBoth(@Param("fristId") String fristId, @Param("secondId") String secondId);
}
