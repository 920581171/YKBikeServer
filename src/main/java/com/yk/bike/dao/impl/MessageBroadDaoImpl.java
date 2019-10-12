package com.yk.bike.dao.impl;

import com.yk.bike.mapper.MessageBroadMapper;
import com.yk.bike.pojo.MessageBroad;
import com.yk.bike.dao.MessageBroadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yk.bike.utils.RandomUtils.randomId;

@Service
public class MessageBroadDaoImpl implements MessageBroadDao {
    @Autowired
    MessageBroadMapper messageBroadDao;

    @Override
    public MessageBroad searchMessageBroadById(String messageId) throws Exception {
        return messageBroadDao.selectOne(MessageBroadMapper.COLUMN_MESSAGE_ID, messageId);
    }

    @Override
    public List<MessageBroad> searchMessageBroadByType(String messageType) throws Exception {
        return messageBroadDao.selectList(MessageBroadMapper.COLUMN_MESSAGE_TYPE, messageType);
    }

    @Override
    public List<MessageBroad> searchMessageBroadBySenderId(String senderId) throws Exception {
        return messageBroadDao.selectList(MessageBroadMapper.COLUMN_SENDER_ID, senderId);
    }

    @Override
    public List<MessageBroad> searchMessageBroadBySenderIdWithType(String senderId, String type) throws Exception {
        return messageBroadDao.selectByType(MessageBroadMapper.COLUMN_SENDER_ID, senderId, type);
    }

    @Override
    public List<MessageBroad> searchMessageBroadByHandler(String handlerId) throws Exception {
        return messageBroadDao.selectList(MessageBroadMapper.COLUMN_HANDLER_ID, handlerId);
    }

    @Override
    public List<MessageBroad> searchMessageBroadByHandlerWithType(String senderId, String type) throws Exception {
        return messageBroadDao.selectByType(MessageBroadMapper.COLUMN_SENDER_ID, senderId, type);
    }

    @Override
    public List<MessageBroad> searchAllMessageBroad() throws Exception {
        return messageBroadDao.selectTable();
    }

    @Override
    public int addMessageBroad(MessageBroad messageBroad) throws Exception {
        String messageId = randomId("MSG");
        while (messageBroadDao.selectOne(MessageBroadMapper.COLUMN_MESSAGE_ID, messageId) != null) {
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
