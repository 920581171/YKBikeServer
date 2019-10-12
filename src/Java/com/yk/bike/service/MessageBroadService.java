package com.yk.bike.service;

public interface MessageBroadService {
    String findAllMessageBroad() throws Exception;

    String findMessageBroadByMessageId(String messageId) throws Exception;

    String findMessageBroadBySenderId(String senderId) throws Exception;

    String findMessageBroadByHandlerIdWithType(String handlerId, String messageType) throws Exception;

    String findMessageBroadByType(String messageType) throws Exception;

    String addMessageBroad(String senderId, String messageContent, String messageType) throws Exception;

    String updateMessageBroad(String messageId, String handlerId, String handlerName, String messageReply) throws Exception;

    String deleteMessageBroad(String messageId) throws Exception;
}
