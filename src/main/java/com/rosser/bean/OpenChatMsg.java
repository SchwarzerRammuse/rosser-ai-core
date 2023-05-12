package com.rosser.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * chat模式的消息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenChatMsg {
    public static String ROLE = "role";
    public static String CONTENT = "content";
    public static String MODEL = "model";
    private List<Message> messageList;
    private String model;
    private boolean stream;
    public OpenChatMsg(String role,String spell,String model,boolean stream){
        OpenChatMsg openChatMsg = new OpenChatMsg();
        List<Message> messageList = new ArrayList<>();
        Message message = new Message();
        message.setContent(spell);
        message.setRole(role);
        messageList.add(message);
        openChatMsg.setMessageList(messageList);
        openChatMsg.setModel(model);
        openChatMsg.setStream(stream);
    }
}

