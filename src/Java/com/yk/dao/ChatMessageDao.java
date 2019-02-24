package com.yk.dao;

import com.yk.pojo.ChatMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatMessageDao extends BaseDao<ChatMessage> {
    String COLUMN_CHAT_ID = "chat_id";
    String COLUMN_FROM_ID = "from_id";
    String COLUMN_TO_ID = "to_id";
    String COLUMN_CHAT_CONTENT_ID = "chat_content";
    String COLUMN_TYPE_ID = "chat_type";
    String COLUMN_SEND_TIME_ID = "send_time";

    List<ChatMessage> selectBoth(@Param("id1") String id1, @Param("id2") String id2);
}
