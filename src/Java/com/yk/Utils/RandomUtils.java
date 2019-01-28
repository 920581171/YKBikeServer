package com.yk.Utils;

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
        StringBuilder stringBuilder = new StringBuilder(dateFormat.format(date));
        int random = (int) (Math.random() * 10000);
        stringBuilder.append(random);

        return stringBuilder.toString();
    }

    /**
     * 创建随机Id
     */
    public static String randomId(String s) {
        int len = 1000*1000*1000;
        for (int i = 1;i<s.length();i++){
            len /=10;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int random = (int) (Math.random() * len);
        stringBuilder.append(s).append(random);

        return stringBuilder.toString();
    }
}
