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
    @RequestMapping(value = "/findAllMessageBroad", method = RequestMethod.POST)
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
    @RequestMapping(value = "/findMessageBroadByMessageId", method = RequestMethod.POST)
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
    @RequestMapping(value = "/findMessageBroadBySenderId", method = RequestMethod.POST)
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
    @RequestMapping(value = "/findMessageBroadByHandlerId", method = RequestMethod.POST)
    public String findMessageBroadByHandlerId(@RequestParam("handlerId") String handlerId) {
        try {
            List<MessageBroad> messageBroads = messageBroadService.searchMessageBroadByHandler(handlerId);
            return GsonUtils.responseObjectJson(messageBroads != null, messageBroads);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加留言板", httpMethod = "POST")
    @RequestMapping(value = "/addMessageBroad", method = RequestMethod.POST)
    public String addMessageBroad(@RequestParam("senderId") String senderId,
                                  @RequestParam("messageContent") String messageContent) {

        MessageBroad messageBroad = new MessageBroad()
                .setSenderId(senderId).setMessageContent(messageContent).setMessageStatus("0");

        try {
            return GsonUtils.responseSimpleJson(messageBroadService.addMessageBroad(messageBroad) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新留言板", httpMethod = "POST")
    @RequestMapping(value = "/updateMessageBroad", method = RequestMethod.POST)
    public String updateSiteLocation(@RequestParam("messageId") String messageId,
                                     @RequestParam("senderId") String senderId,
                                     @RequestParam("handlerId") String handlerId,
                                     @RequestParam("messageContent") String messageContent,
                                     @RequestParam("messageStatus") String messageStatus) {

        try {
            MessageBroad messageBroad = messageBroadService.searchMessageBroadById(messageId);
            if (messageBroad == null)
                return GsonUtils.responseErrorMsgJson("没有找到该留言");
            messageBroad.setSenderId(senderId)
                    .setHandlerId(handlerId)
                    .setMessageContent(messageContent)
                    .setMessageStatus(messageStatus);


            return GsonUtils.responseSimpleJson(messageBroadService.updateMessageBroad(messageBroad) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "删除留言板", httpMethod = "POST")
    @RequestMapping(value = "/deleteMessageBroad", method = RequestMethod.POST)
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
