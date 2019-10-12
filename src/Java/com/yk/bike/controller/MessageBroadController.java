package com.yk.bike.controller;

import com.yk.bike.service.MessageBroadService;
import com.yk.bike.utils.GsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "留言板相关")
@Controller
@RequestMapping(value = "/messagebroad")
public class MessageBroadController {

    @Autowired
    private MessageBroadService messageBroadService;

    @ResponseBody
    @ApiOperation(value = "查询所有留言板", httpMethod = "POST")
    @RequestMapping(value = "/findAllMessageBroad", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findAllMessageBroad() {
        try {
            return messageBroadService.findAllMessageBroad();
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
            return messageBroadService.findMessageBroadByMessageId(messageId);
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
            return messageBroadService.findMessageBroadBySenderId(senderId);
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
            return messageBroadService.findMessageBroadByHandlerIdWithType(handlerId, messageType);
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
            return messageBroadService.findMessageBroadByType(messageType);
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

        try {
            return messageBroadService.addMessageBroad(senderId, messageContent, messageType);
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
            return messageBroadService.updateMessageBroad(messageId, handlerId, handlerName, messageReply);
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
            return messageBroadService.deleteMessageBroad(messageId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }
}
