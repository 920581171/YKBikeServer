package com.yk.Utils;

import com.google.gson.Gson;
import com.yk.response.ErrorCommonResponse;
import com.yk.response.SuccessCommonResponse;

import java.lang.reflect.Type;

public class GsonUtils {
    private static Gson gson;

    private GsonUtils() {
    }

    private static Gson getGson() {
        if (gson == null) {
            synchronized (GsonUtils.class) {
                if (gson == null) {
                    gson = new Gson().newBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                }
            }
        }
        return gson;
    }

    public static String toJson(Object object) {
        return getGson().toJson(object);
    }

    public static <T>T fromJson(String string, Type type) throws IllegalStateException{
        return getGson().fromJson(string,type);
    }

    public static String responseSimpleJson(boolean isSuccess) {
        return isSuccess ?
                GsonUtils.responseSuccessJson() :
                GsonUtils.responseErrorJson();
    }

    public static String responseObjectJson(boolean isSuccess,Object object) {
        return isSuccess ?
                GsonUtils.responseSuccessObjJson(object) :
                GsonUtils.responseErrorJson();
    }

    public static String responseMsgObjJson(boolean isSuccess,String msg,Object object){
        return isSuccess?
                GsonUtils.responseSuccessObjJson(object):
                GsonUtils.responseErrorMsgJson(msg);
    }

    public static String responseSuccessJson() {
        return getGson().toJson(new SuccessCommonResponse());
    }

    public static String responseSuccessObjJson(Object object) {
        return getGson().toJson(new SuccessCommonResponse(object));
    }

    public static String responseSuccessMsgJson(String msg) {
        return getGson().toJson(new SuccessCommonResponse(msg));
    }

    public static String responseSuccessMsgObjJson(String msg,Object object){
        return getGson().toJson(new SuccessCommonResponse(msg,object));
    }

    public static String responseErrorJson() {
        return getGson().toJson(new ErrorCommonResponse());
    }

    public static String responseErrorObjJson(Object object) {
        return getGson().toJson(new ErrorCommonResponse(object));
    }

    public static String responseErrorMsgJson(String msg) {
        return getGson().toJson(new ErrorCommonResponse(msg));
    }

    public static String responseErrorMsgObjJson(String msg,Object object){
        return getGson().toJson(new ErrorCommonResponse(msg,object));
    }
}
