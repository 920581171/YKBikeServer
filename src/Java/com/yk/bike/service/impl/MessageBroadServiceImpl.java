package com.yk.bike.service.impl;

import com.yk.bike.dao.impl.MessageBroadDaoImpl;
import com.yk.bike.pojo.MessageBroad;
import com.yk.bike.service.MessageBroadService;
import com.yk.bike.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageBroadServiceImpl implements MessageBroadService {

    @Autowired
    private MessageBroadDaoImpl messageBroadDao;

    @Override
    public String findAllMessageBroad() throws Exception {
        List<MessageBroad> messageBroads = messageBroadDao.searchAllMessageBroad();
        return GsonUtils.responseObjectJson(messageBroads != null, messageBroads);
    }

    @Override
    public String findMessageBroadByMessageId(String messageId) throws Exception {
        MessageBroad messageBroad = messageBroadDao.searchMessageBroadById(messageId);
        return GsonUtils.responseObjectJson(messageBroad != null, messageBroad);
    }

    @Override
    public String findMessageBroadBySenderId(String senderId) throws Exception {
        List<MessageBroad> messageBroads = messageBroadDao.searchMessageBroadBySenderId(senderId);
        return GsonUtils.responseObjectJson(messageBroads != null, messageBroads);
    }

    @Override
    public String findMessageBroadByHandlerIdWithType(String handlerId, String messageType) throws Exception {
        List<MessageBroad> messageBroads = messageBroadDao.searchMessageBroadByHandlerWithType(handlerId, messageType);
        return GsonUtils.responseObjectJson(messageBroads != null, messageBroads);
    }

    @Override
    public String findMessageBroadByType(String messageType) throws Exception {
        List<MessageBroad> messageBroads = messageBroadDao.searchMessageBroadByType(messageType);
        return GsonUtils.responseObjectJson(messageBroads != null, messageBroads);
    }

    @Override
    public String addMessageBroad(String senderId, String messageContent, String messageType) throws Exception {
        MessageBroad messageBroad = new MessageBroad()
                .setSenderId(senderId).setMessageContent(messageContent).setMessageType(messageType).setMessageStatus("0");
        return GsonUtils.responseSimpleJson(messageBroadDao.addMessageBroad(messageBroad) > 0);
    }

    @Override
    public String updateMessageBroad(String messageId, String handlerId, String handlerName, String messageReply) throws Exception {
        MessageBroad messageBroad = messageBroadDao.searchMessageBroadById(messageId);
        if (messageBroad == null) {
            return GsonUtils.responseErrorMsgJson("没有找到该留言");
        }
        messageBroad.setHandlerId(handlerId)
                .setHandlerName(handlerName)
                .setMessageReply(messageReply)
                .setMessageStatus("1");


        return GsonUtils.responseSimpleJson(messageBroadDao.updateMessageBroad(messageBroad) > 0);
    }

    @Override
    public String deleteMessageBroad(String messageId) throws Exception {
        MessageBroad messageBroad = messageBroadDao.searchMessageBroadById(messageId);
        if (messageBroad == null) {
            return GsonUtils.responseErrorMsgJson("没有找到该留言");
        }
        return GsonUtils.responseSimpleJson(messageBroadDao.deleteMessageBroad(messageBroad) > 0);
    }
}
