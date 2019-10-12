package com.yk.bike.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomUtils {
    /**
     * 创建随机userId
     */
    public static String randomUserId() {
        DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        Date date = new Date();
        return randomId(dateFormat.format(date));
    }

    /**
     * 创建随机Id
     */
    public static String randomId(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s);
        for (int i = 0; i < 10 - s.length(); i++) {
            int random = (int) (Math.random() * 10);
            stringBuilder.append(random);
        }
        return stringBuilder.toString();
    }
}
