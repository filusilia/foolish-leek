package com.ilia.leek.util.fund;

import java.util.HashMap;
import java.util.Map;

/**
 * 尝试模拟浏览器请求工具类
 *
 * @author Alice on 2022/1/17
 * @version 1.0
 * @since 1.0
 */
public class RequestTool {
    public final static String[] HEAD_CONNECTION = new String[]{"keep-alive", "close"};
    public final static String[] HEAD_ACCEPT = new String[]{"text/html, application/xhtml+xml, */*"};
    public final static String[] HEAD_ACCEPT_LANGUAGE = new String[]{
            "zh-CN,zh;q=0.9",
            "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2",
            "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6",
    };
    public final static String[] HEAD_USER_AGENT = new String[]{
            //chrome
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36",
            //firefox
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:98.0) Gecko/20100101 Firefox/98.0",
            //edge
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.80 Safari/537.36 Edg/98.0.1108.50",
    };

    public final static String SEC_CH_UA = "\"Not A;Brand\";v=\"99\", \"Chromium\";v=\"98\", \"Microsoft Edge\";v=\"98\"";

    public final static Map<String, String> HEADER = new HashMap<>();

    static {
        HEADER.put("Connection", HEAD_CONNECTION[0]);
        HEADER.put("Accept", HEAD_ACCEPT[0]);
        HEADER.put("Accept-Language", HEAD_ACCEPT_LANGUAGE[1]);
        HEADER.put("User-Agent", HEAD_USER_AGENT[0]);
        HEADER.put("sec-ch-ua", SEC_CH_UA);
    }
}
