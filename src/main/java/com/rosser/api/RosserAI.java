package com.rosser.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rosser.bean.OpenChatMsg;
import com.rosser.config.Properties;
import com.rosser.enumerate.Model;
import com.rosser.enumerate.Role;
import com.rosser.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class RosserAI {
    private Properties properties;
    private OpenAIService openAIService;

    public RosserAI(Properties properties, OpenAIService openAIService) {
        this.properties = properties;
        this.openAIService = openAIService;
    }

    public String chat(String spell) {
        OpenChatMsg openChatMsg = new OpenChatMsg(Role.USER.getValue(),spell,Model.GPT35TURBO.getValue(),true);
        return chat(openChatMsg);

    }
    public String chat(OpenChatMsg openChatMsg)  {
        String answer = openAIService.chat(openChatMsg,properties.getApikey());
        return answer;

    }
}
