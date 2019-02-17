package com.yk.controller;

import com.yk.Utils.GsonUtils;
import com.yk.impl.AdminInfoServiceImpl;
import com.yk.impl.UserInfoServiceImpl;
import com.yk.pojo.AdminInfo;
import com.yk.pojo.UserInfo;
import com.yk.pojo.WebSocketMessage;
import com.yk.pojo.WebSocketParam;
import org.springframework.web.context.ContextLoader;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.yk.constant.Consts.*;

@ServerEndpoint("/WebSocketHandler/{param}")
public class WebSocketHandler {

    UserInfoServiceImpl userInfoService = (UserInfoServiceImpl) ContextLoader.getCurrentWebApplicationContext().getBean("userInfoServiceImpl");
    AdminInfoServiceImpl adminInfoService = (AdminInfoServiceImpl) ContextLoader.getCurrentWebApplicationContext().getBean("adminInfoServiceImpl");

    private static Map<String, WebSocketHandler> clients = new ConcurrentHashMap<>();
    private Session session;
    private String loginId;

    @OnOpen
    public void onOpen(@PathParam("param") String param, Session session) throws IOException {

        param = URLDecoder.decode(param, "utf-8");

        this.loginId = param;
        this.session = session;

        clients.put(loginId, this);
        session.getBasicRemote().sendText(GsonUtils.toJson(new WebSocketMessage().setType(WEBSOCKET_TYPE_GET_PARAM)));

        System.out.println("建立连接：" + loginId + " 当前连接数：" + clients.size());
    }

    @OnClose
    public void onClose() throws IOException {
        clients.remove(loginId);
        System.out.println("断开链接：" + loginId + " 当前连接数：" + clients.size());
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        WebSocketMessage webSocketMessage = GsonUtils.fromJson(message, WebSocketMessage.class);
        if (webSocketMessage.getType() == WEBSOCKET_TYPE_GET_PARAM) {
            WebSocketParam param = GsonUtils.fromJson((String) webSocketMessage.getData(), WebSocketParam.class);
            boolean b = param.getLoginType().equals(LOGIN_TYPE_ADMIN) ?
                    checkAdminInfo(param.getPassword()) :
                    checkUserInfo(param.getPassword());
            if (b)
                System.out.println("校验成功：" + loginId);
            else
                session.close();
        }
    }

    @OnMessage
    public void onMessage(byte[] bytes) throws IOException {
        System.out.println(bytes.length);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, String To) throws IOException {
        for (WebSocketHandler item : clients.values()) {
            if (item.loginId.equals(To))
                item.session.getAsyncRemote().sendText(message);
        }
    }

    public void sendMessageAll(String message) throws IOException {
        for (WebSocketHandler item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public void sendBinaryAll(byte[] bytes) throws IOException {
        for (WebSocketHandler item : clients.values()) {
            if (item == this)
                continue;
            //异步发送
//                item.session.getAsyncRemote().sendBinary(ByteBuffer.wrap(bytes));
            //同步发送
            item.session.getBasicRemote().sendBinary(ByteBuffer.wrap(bytes));
        }
    }

    private boolean checkAdminInfo(String password) {
        try {
            AdminInfo adminInfo = adminInfoService.searchAdminId(loginId);
            if (adminInfo.getAdminPassword().equals(password))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkUserInfo(String password) {
        try {
            UserInfo userInfo = userInfoService.searchUserId(loginId);
            if (userInfo.getUserPassword().equals(password))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
