package com.yk.bike.mapper;

import com.yk.bike.pojo.MessageBroad;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageBroadMapper extends BaseMapper<MessageBroad> {
    String COLUMN_MESSAGE_ID = "message_id";
    String COLUMN_SENDER_ID = "sender_id";
    String COLUMN_HANDLER_ID = "handler_id";
    String COLUMN_HANDLER_NAME = "handler_name";
    String COLUMN_MESSAGE_CONTENT = "message_content";
    String COLUMN_MESSAGE_REPLY = "message_reply";
    String COLUMN_MESSAGE_STATUS = "message_status";
    String COLUMN_MESSAGE_TYPE = "message_type";

    List<MessageBroad> selectByType(@Param("column") String column,@Param("property") String property,@Param("type") String type);
}
