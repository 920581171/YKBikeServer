package com.yk.Utils;

import com.google.gson.Gson;

public class GsonUtils {
    private static GsonUtils instance;

    private GsonUtils(){}

    public static GsonUtils getInstance() {
        if (instance==null){
            synchronized (GsonUtils.class){
                if (instance==null){
                    instance = new GsonUtils();
                }
            }
        }
        return instance;
    }

    private Gson gson = new Gson();

    public Gson getGson() {
        return gson;
    }
}
