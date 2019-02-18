package com.yk.dao;

import com.yk.pojo.MessageBroad;

public interface MessageBroadDao extends BaseDao<MessageBroad>{
    String COLUMN_MESSAGE_ID = "message_id";
    String COLUMN_SENDER_ID = "sender_id";
    String COLUMN_HANDLER_ID = "handler_id";
    String COLUMN_MESSAGE_CONTENT = "message_content";
    String COLUMN_MESSAGE_STATUS = "message_status";
}
