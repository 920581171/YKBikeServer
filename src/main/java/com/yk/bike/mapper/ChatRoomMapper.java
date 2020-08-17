package com.yk.bike.mapper;

import com.yk.bike.pojo.ChatRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChatRoomMapper extends BaseMapper<ChatRoom> {
    String COLUMN_ROOM_ID = "room_id";
    String COLUMN_FRIST_ID = "frist_id";
    String COLUMN_SECOND_ID = "second_id";

    ChatRoom selectBoth(@Param("fristId") String fristId, @Param("secondId") String secondId);
}
