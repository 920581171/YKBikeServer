package com.yk.controller;

import com.yk.Utils.GsonUtils;
import com.yk.impl.ChatMessageServiceImpl;
import com.yk.pojo.ChatMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(description = "聊天记录相关")
@Controller
@RequestMapping(value = "/chatmessage")
public class ChatMessageController {
    @Autowired
    ChatMessageServiceImpl chatMessageService;

    @ApiOperation(value = "根据双方id查找聊天记录")
    @ResponseBody
    @RequestMapping(value = "/findALLChatMessageByBothId", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String findALLChatMessageByBothId(@RequestParam("id1") String id1, @RequestParam("id2") String id2) {
        try {
            List<ChatMessage> chatMessages = chatMessageService.searchAllChatMessageByBothId(id1, id2);
            return GsonUtils.responseObjectJson(chatMessages != null, chatMessages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return GsonUtils.responseErrorJson();
    }
}
