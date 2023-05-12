package com.rosser.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rosser.bean.Message;
import com.rosser.bean.OpenChatMsg;
import com.rosser.enumerate.Model;
import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.List;

/**
 * 通用工具类
 */
public class CommonUtil {
    public static JSONObject buildchatRequestBody(OpenChatMsg openChatMsg){
        // 设置请求体
        JSONObject requestBody = new JSONObject();
        JSONArray messages = buildMessageList(openChatMsg.getMessageList());
        requestBody.put("model", Model.GPT35TURBO.getValue());
        requestBody.put("messages",messages);
        return requestBody;
    }
    public static JSONObject buildMessage(Message message){
        JSONObject json = new JSONObject();
        json.put(OpenChatMsg.ROLE,message.getRole());
        json.put(OpenChatMsg.CONTENT,message.getContent());
        return json;
    }
    public static JSONArray buildMessageList(List<Message> messageList){
        JSONArray messages = new JSONArray();
        for (int i = 0; i <messageList.size() ; i++) {
            messages.add(buildMessage(messageList.get(i)));
        }

        return messages;
    }
}
