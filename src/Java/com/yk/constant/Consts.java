package com.yk.constant;

public class Consts {
    public static final int COMMON_RESPONSE_SUCCESS_CODE = 1;
    public static final int COMMON_RESPONSE_ERROR_CODE = -1;
    public static final String COMMON_RESPONSE_SUCCESS_MSG = "SUCCESS";
    public static final String COMMON_RESPONSE_ERROR_MSG = "ERROR";
    public static final String SEARCH_USER_RESPONSE_ERROR_MSG = "用户已存在";

    public static final int WEBSOCKET_TYPE_FORCE_LOGOUT = -1;
    public static final int WEBSOCKET_TYPE_GET_PARAM = 0;
    public static final int WEBSOCKET_TYPE_CHAT = 1;
    public static final int WEBSOCKET_TYPE_LOCATION = 2;
    public static final int WEBSOCKET_TYPE_STOP_LOCATION = 3;

    public static final String ROOT_PATH = System.getProperty("root");
    public static final String QR_CODE_PATH = ROOT_PATH + "QRCode";
    public static final String AVATAR_PATH = ROOT_PATH + "AVATAR";

    public static final String LOGIN_TYPE_PHONE = "typePhone";
    public static final String LOGIN_TYPE_USER = "typeUser";
    public static final String LOGIN_TYPE_ADMIN = "typeAdmin";

    public static final String LOGIN_DEVICE_MOBILE = "mobile";
    public static final String LOGIN_DEVICE_WEB = "web";
}
