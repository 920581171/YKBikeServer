package com.yk.bike.dao;

import com.yk.bike.pojo.MessageBroad;

import java.util.List;

public interface MessageBroadDao {
    MessageBroad searchMessageBroadById(String messageId) throws Exception;

    List<MessageBroad> searchMessageBroadByType(String senderId) throws Exception;

    List<MessageBroad> searchMessageBroadBySenderId(String senderId) throws Exception;

    List<MessageBroad> searchMessageBroadBySenderIdWithType(String handlerId,String type) throws Exception;

    List<MessageBroad> searchMessageBroadByHandler(String handlerId) throws Exception;

    List<MessageBroad> searchMessageBroadByHandlerWithType(String handlerId,String type) throws Exception;

    List<MessageBroad> searchAllMessageBroad() throws Exception;

    int addMessageBroad(MessageBroad messageBroad) throws Exception;

    int updateMessageBroad(MessageBroad messageBroad) throws Exception;

    int deleteMessageBroad(MessageBroad messageBroad) throws Exception;
}
