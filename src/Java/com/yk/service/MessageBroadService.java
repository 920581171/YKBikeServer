package com.yk.service;

import com.yk.pojo.MessageBroad;

import java.util.List;

public interface MessageBroadService {
    MessageBroad searchMessageBroadById(String messageId) throws Exception;

    List<MessageBroad> searchMessageBroadBySenderId(String senderId) throws Exception;

    List<MessageBroad> searchMessageBroadByHandler(String handlerId) throws Exception;

    List<MessageBroad> searchAllMessageBroad() throws Exception;

    int addMessageBroad(MessageBroad messageBroad) throws Exception;

    int updateMessageBroad(MessageBroad messageBroad) throws Exception;

    int deleteMessageBroad(MessageBroad messageBroad) throws Exception;
}
