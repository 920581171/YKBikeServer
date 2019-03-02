package com.yk.controller;

import com.yk.Utils.GsonUtils;
import com.yk.impl.MessageBroadServiceImpl;
import com.yk.pojo.MessageBroad;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(description = "留言板相关")
@Controller
@RequestMapping(value = "/messagebroad")
public class MessageBroadController {

    @Autowired
    MessageBroadServiceImpl messageBroadService;

    @ResponseBody
    @ApiOperation(value = "查询所有留言板", httpMethod = "POST")
    @RequestMapping(value = "/findAllMessageBroad", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findAllMessageBroad() {
        try {
            List<MessageBroad> messageBroads = messageBroadService.searchAllMessageBroad();
            return GsonUtils.responseObjectJson(messageBroads != null, messageBroads);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据messageId查询留言板", httpMethod = "POST")
    @RequestMapping(value = "/findMessageBroadByMessageId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findMessageBroadByMessageId(@RequestParam("messageId") String messageId) {
        try {
            MessageBroad messageBroad = messageBroadService.searchMessageBroadById(messageId);
            return GsonUtils.responseObjectJson(messageBroad != null, messageBroad);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据发送者Id查询留言板", httpMethod = "POST")
    @RequestMapping(value = "/findMessageBroadBySenderId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findMessageBroadBySenderId(@RequestParam("senderId") String senderId) {
        try {
            List<MessageBroad> messageBroads = messageBroadService.searchMessageBroadBySenderId(senderId);
            return GsonUtils.responseObjectJson(messageBroads != null, messageBroads);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据处理者Id查询留言板", httpMethod = "POST")
    @RequestMapping(value = "/findMessageBroadByHandlerIdWithType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findMessageBroadByHandlerIdWithType(@RequestParam("handlerId") String handlerId, @RequestParam("messageType") String messageType) {
        try {
            List<MessageBroad> messageBroads = messageBroadService.searchMessageBroadByHandlerWithType(handlerId, messageType);
            return GsonUtils.responseObjectJson(messageBroads != null, messageBroads);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据messageType查询所有留言板", httpMethod = "POST")
    @RequestMapping(value = "/findMessageBroadByType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findMessageBroadByType(@RequestParam("messageType") String messageType) {
        try {
            List<MessageBroad> messageBroads = messageBroadService.searchMessageBroadByType(messageType);
            return GsonUtils.responseObjectJson(messageBroads != null, messageBroads);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加留言板", httpMethod = "POST")
    @RequestMapping(value = "/addMessageBroad", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addMessageBroad(@RequestParam("senderId") String senderId,
                                  @RequestParam("messageContent") String messageContent,
                                  @RequestParam("messageType") String messageType) {

        MessageBroad messageBroad = new MessageBroad()
                .setSenderId(senderId).setMessageContent(messageContent).setMessageType(messageType).setMessageStatus("0");

        try {
            return GsonUtils.responseSimpleJson(messageBroadService.addMessageBroad(messageBroad) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新留言板", httpMethod = "POST")
    @RequestMapping(value = "/updateMessageBroad", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateMessageBroad(@RequestParam("messageId") String messageId,
                                     @RequestParam("handlerId") String handlerId,
                                     @RequestParam("handlerName") String handlerName,
                                     @RequestParam("messageReply") String messageReply) {

        try {
            MessageBroad messageBroad = messageBroadService.searchMessageBroadById(messageId);
            if (messageBroad == null)
                return GsonUtils.responseErrorMsgJson("没有找到该留言");
            messageBroad.setHandlerId(handlerId)
                    .setHandlerName(handlerName)
                    .setMessageReply(messageReply)
                    .setMessageStatus("1");


            return GsonUtils.responseSimpleJson(messageBroadService.updateMessageBroad(messageBroad) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "删除留言板", httpMethod = "POST")
    @RequestMapping(value = "/deleteMessageBroad", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteMessageBroad(@RequestParam("messageId") String messageId) {
        try {
            MessageBroad messageBroad = messageBroadService.searchMessageBroadById(messageId);
            if (messageBroad == null)
                return GsonUtils.responseErrorMsgJson("没有找到该留言");
            return GsonUtils.responseSimpleJson(messageBroadService.deleteMessageBroad(messageBroad) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }
}
