package com.yk.bike.controller;

import com.yk.bike.service.impl.ChatMessageServiceImpl;
import com.yk.bike.utils.GsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "聊天记录相关")
@Controller
@RequestMapping(value = "/chatmessage")
public class ChatMessageController {

    @Autowired
    private ChatMessageServiceImpl chatMessageService;

    @ApiOperation(value = "根据双方id查找聊天记录")
    @ResponseBody
    @RequestMapping(value = "/findALLChatMessageByBothId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findALLChatMessageByBothId(@RequestParam("id1") String id1, @RequestParam("id2") String id2) {
        try {
            return chatMessageService.findALLChatMessageByBothId(id1, id2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return GsonUtils.responseErrorJson();
    }
}
