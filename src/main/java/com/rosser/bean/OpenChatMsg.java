package com.rosser.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String role;
    private String content;
    private String model;

}
