package com.rosser.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rosser.bean.OpenChatMsg;

/**
 * 通用工具类
 */
public class CommonUtil {
    public static JSONObject buildchatRequestBody(OpenChatMsg openChatMsg){
        // 设置请求体
        JSONObject requestBody = new JSONObject();
        JSONArray messages = new JSONArray();
        JSONObject message1 = new JSONObject();
        message1.put(OpenChatMsg.ROLE,openChatMsg.getRole());
        message1.put(OpenChatMsg.CONTENT,openChatMsg.getContent());
        //requestBody.put("prompt", prompt);
        requestBody.put(OpenChatMsg.MODEL,openChatMsg.getModel());
        messages.add(message1);
        requestBody.put("messages",messages);
        return requestBody;
    }
}
