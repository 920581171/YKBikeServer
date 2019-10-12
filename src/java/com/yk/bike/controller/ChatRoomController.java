package com.yk.bike.controller;

import com.yk.bike.service.ChatRoomService;
import com.yk.bike.utils.GsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "聊天记录列表相关")
@Controller
@RequestMapping(value = "/chatroom")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @ApiOperation(value = "根据fristId查找聊天人员")
    @ResponseBody
    @RequestMapping(value = "/findALLChatRoomByFristId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findALLChatRoomByFristId(@RequestParam("fristId") String fristId) {
        try {
            return chatRoomService.findALLChatRoomByFristId(fristId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return GsonUtils.responseErrorJson();
    }
}
