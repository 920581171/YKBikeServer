package com.yk.bike.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.yk.bike.dao.AdminInfoDao;
import com.yk.bike.dao.ChatMessageDao;
import com.yk.bike.dao.ChatRoomDao;
import com.yk.bike.dao.UserInfoDao;
import com.yk.bike.dao.impl.AdminInfoDaoImpl;
import com.yk.bike.dao.impl.ChatMessageDaoImpl;
import com.yk.bike.dao.impl.ChatRoomDaoImpl;
import com.yk.bike.dao.impl.UserInfoDaoImpl;
import com.yk.bike.pojo.*;
import com.yk.bike.utils.GsonUtils;
import org.springframework.web.context.ContextLoader;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.*;

import static com.yk.bike.constant.Consts.*;

@ServerEndpoint("/WebSocketHandler/{param}")
public class WebSocketHandler {

    private UserInfoDao userInfoDao = (UserInfoDaoImpl) ContextLoader.getCurrentWebApplicationContext().getBean("userInfoDaoImpl");
    private AdminInfoDao adminInfoDao = (AdminInfoDaoImpl) ContextLoader.getCurrentWebApplicationContext().getBean("adminInfoDaoImpl");
    private ChatMessageDao chatMessageDao = (ChatMessageDaoImpl) ContextLoader.getCurrentWebApplicationContext().getBean("chatMessageDaoImpl");
    private ChatRoomDao chatRoomDao = (ChatRoomDaoImpl) ContextLoader.getCurrentWebApplicationContext().getBean("chatRoomDaoImpl");

    public static Map<String, WebSocketHandler> clients = new ConcurrentHashMap<>();
    private static ExecutorService executorService = new ThreadPoolExecutor(12, 12, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(), new ThreadFactoryBuilder().build());
    private Session session;
    private String loginId;
    private boolean isWeb = false;
    private boolean toRoom = false;
    private boolean fromRoom = false;
    private boolean isCheck = false;

    @OnOpen
    public void onOpen(@PathParam("param") String param, final Session session) throws Exception {
        this.loginId = param;
        this.session = session;

        session.getBasicRemote().sendText(GsonUtils.toJson(new WebSocketMessage().setType(WEBSOCKET_TYPE_GET_PARAM)));

        checkTimeOut();
    }

    @OnClose
    public void onClose() throws Exception {
        clients.remove(loginId);
        System.out.println("断开连接：" + loginId +
                " SESSION：" + System.identityHashCode(session) +
                " 当前连接数：" + clients.size() +
                " 设备类型：" + (isWeb ? LOGIN_DEVICE_WEB : LOGIN_DEVICE_MOBILE));
    }

    @OnMessage
    public void onMessage(String message) throws Exception {
        WebSocketMessage webSocketMessage = GsonUtils.fromJson(message, WebSocketMessage.class);
        if (webSocketMessage.getType() == WEBSOCKET_TYPE_GET_PARAM) {
            WebSocketParam param = GsonUtils.fromJson((String) webSocketMessage.getData(), WebSocketParam.class);
            boolean b = param.getLoginType().equals(LOGIN_TYPE_ADMIN) ?
                    checkAdminInfo(param.getPassword()) :
                    checkUserInfo(param.getPassword());
            isWeb = param.getLoginDevice().endsWith(LOGIN_DEVICE_WEB);
            if (b) {
                checkLogin();
            } else {
                session.close();
                isCheck = true;
            }
        } else if (webSocketMessage.getType() == WEBSOCKET_TYPE_CHAT) {
            String chat = (String) (webSocketMessage.getData());
            ChatMessage chatMessage = GsonUtils.fromJson(chat, ChatMessage.class);
            chatMessageDao.addChatMessage(chatMessage);
            if (!toRoom) {
                toRoom = chatRoomDao.searchBothAndAdd(chatMessage.getToId(), chatMessage.getFromId()) != null;
            }
            if (fromRoom) {
                fromRoom = chatRoomDao.searchBothAndAdd(chatMessage.getFromId(), chatMessage.getToId()) != null;
            }
            sendMessageTo(message, chatMessage.getToId());
        } else if (webSocketMessage.getType() == WEBSOCKET_TYPE_LOCATION || webSocketMessage.getType() == WEBSOCKET_TYPE_STOP_LOCATION) {
            for (WebSocketHandler webSocketHandler : clients.values()) {
                if (webSocketHandler.isWeb) {
                    webSocketHandler.session.getBasicRemote().sendText(message);
                    System.out.println(message);
                }
            }
        }
    }

    @OnMessage
    public void onMessage(byte[] bytes) throws Exception {
        System.out.println(bytes.length);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, String toId) throws Exception {
        WebSocketHandler toHandler = clients.get(toId);
        if (toHandler != null) {
            toHandler.session.getBasicRemote().sendText(message);
        }
    }

    public void sendMessageAll(String message) throws Exception {
        for (WebSocketHandler item : clients.values()) {
            item.session.getBasicRemote().sendText(message);
        }
    }

    public void sendBinaryAll(byte[] bytes) throws Exception {
        for (WebSocketHandler item : clients.values()) {
            if (item == this) {
                continue;
            }
            //异步发送
//                item.session.getAsyncRemote().sendBinary(ByteBuffer.wrap(bytes));
            //同步发送
            item.session.getBasicRemote().sendBinary(ByteBuffer.wrap(bytes));
        }
    }

    private void checkLogin() throws Exception {
        isCheck = true;
        WebSocketHandler webSocketHandler = clients.get(loginId);
        if (webSocketHandler == null) {
            clients.put(loginId, WebSocketHandler.this);
            System.out.println("建立连接：" + loginId +
                    " SESSION：" + System.identityHashCode(session) +
                    " 当前连接数：" + clients.size() +
                    " 设备类型：" + (isWeb ? LOGIN_DEVICE_WEB : LOGIN_DEVICE_MOBILE));
        } else {
            webSocketHandler.session.getBasicRemote().sendText(GsonUtils.toJson(new WebSocketMessage().setType(WEBSOCKET_TYPE_FORCE_LOGOUT)));
            webSocketHandler.session.close();
            clients.put(loginId, WebSocketHandler.this);
            System.out.println("强制登录：" + loginId +
                    " SESSION：" + System.identityHashCode(session) +
                    " 强制下线SESSION：" + System.identityHashCode(webSocketHandler.session) +
                    " 当前连接数：" + clients.size() +
                    " 设备类型：" + (isWeb ? LOGIN_DEVICE_WEB : LOGIN_DEVICE_MOBILE));
        }
    }

    private boolean checkAdminInfo(String password) {
        try {
            AdminInfo adminInfo = adminInfoDao.searchAdminId(loginId);
            if (adminInfo != null && adminInfo.getAdminPassword().equals(password)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkUserInfo(String password) {
        try {
            UserInfo userInfo = userInfoDao.searchUserId(loginId);
            if (userInfo.getUserPassword().equals(password)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void checkTimeOut() {
        executorService.execute(new CheckTimeOut());
    }

    public class CheckTimeOut implements Runnable {
        @Override
        public void run() {
            int timeout = 5;
            while (!isCheck) {
                try {
                    Thread.sleep(1000);
                    timeout--;
                    if (timeout < 0) {
                        System.out.println("验证超时：" + loginId +
                                " SESSION：" + System.identityHashCode(session) +
                                " 当前连接数：" + clients.size() +
                                " 设备类型：" + (isWeb ? LOGIN_DEVICE_WEB : LOGIN_DEVICE_MOBILE));
                        session.close();
                        isCheck = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
