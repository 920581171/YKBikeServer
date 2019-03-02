package com.yk.controller;

import com.yk.Utils.GsonUtils;
import com.yk.impl.ChatRoomServiceImpl;
import com.yk.pojo.ChatRoom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(description = "聊天记录列表相关")
@Controller
@RequestMapping(value = "/chatroom")
public class ChatRoomController {
    @Autowired
    ChatRoomServiceImpl chatRoomService;

    @ApiOperation(value = "根据fristId查找聊天人员")
    @ResponseBody
    @RequestMapping(value = "/findALLChatRoomByFristId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findALLChatRoomByFristId(@RequestParam("fristId") String fristId) {
        try {
            List<ChatRoom> chatRooms = chatRoomService.searchByFristId(fristId);
            return GsonUtils.responseObjectJson(chatRooms != null, chatRooms);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return GsonUtils.responseErrorJson();
    }
}
