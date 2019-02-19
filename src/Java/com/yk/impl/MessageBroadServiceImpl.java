package com.yk.impl;

import com.yk.dao.MessageBroadDao;
import com.yk.pojo.MessageBroad;
import com.yk.service.MessageBroadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yk.Utils.RandomUtils.randomId;

@Service
public class MessageBroadServiceImpl implements MessageBroadService {
    @Autowired
    MessageBroadDao messageBroadDao;

    @Override
    public MessageBroad searchMessageBroadById(String messageId) throws Exception {
        return messageBroadDao.selectOne(MessageBroadDao.COLUMN_MESSAGE_ID, messageId);
    }

    @Override
    public List<MessageBroad> searchMessageBroadBySenderId(String senderId, String type) throws Exception {
        return messageBroadDao.selectByType(MessageBroadDao.COLUMN_SENDER_ID, senderId, type);
    }

    @Override
    public List<MessageBroad> searchMessageBroadByHandler(String handlerId, String type) throws Exception {
        return messageBroadDao.selectByType(MessageBroadDao.COLUMN_HANDLER_ID, handlerId, type);
    }

    @Override
    public List<MessageBroad> searchAllMessageBroad() throws Exception {
        return messageBroadDao.selectTable();
    }

    @Override
    public int addMessageBroad(MessageBroad messageBroad) throws Exception {
        String messageId = randomId("MSG");
        while (messageBroadDao.selectOne(MessageBroadDao.COLUMN_MESSAGE_ID, messageId) != null) {
            messageId = randomId("MSG");
        }
        messageBroad.setMessageId(messageId);
        messageBroadDao.insert(messageBroad);
        return messageBroad.getId();
    }

    @Override
    public int updateMessageBroad(MessageBroad messageBroad) throws Exception {
        return messageBroadDao.update(messageBroad);
    }

    @Override
    public int deleteMessageBroad(MessageBroad messageBroad) throws Exception {
        return messageBroadDao.delete(messageBroad);
    }
}
