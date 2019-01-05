package com.yk.Utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtils {
    public static void print(HttpServletResponse rp, Object o) {
        try {
            rp.setContentType("text/javascript;charset=utf-8"); // 设置响应报文的编码格式
            PrintWriter printWriter = rp.getWriter(); // 获取 response 的输出流
            printWriter.println(GsonUtils.getGson().toJson(o)); // 通过输出流把业务逻辑的结果输出
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
