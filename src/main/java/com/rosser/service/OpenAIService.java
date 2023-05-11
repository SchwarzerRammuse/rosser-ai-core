package com.rosser.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rosser.bean.OpenChatMsg;
import com.rosser.config.Properties;
import com.rosser.connect.OpenAIConnecter;
import com.rosser.util.CommonUtil;

/**
 * 各个功能的核心实现类
 */
public class OpenAIService {

    private  Properties properties;
    public OpenAIService(Properties properties) {
        this.properties = properties;
    }

    /**
     * 聊天功能,单条聊天
     * @param openChatMsg
     * @param apikey
     * @return
     */
    public String chat(OpenChatMsg openChatMsg, String apikey){
        JSONObject requestBody = CommonUtil.buildchatRequestBody(openChatMsg);
        //api路径
        String url = properties.getChatApiUrl();
        //代理的host
        String proxyHost = properties.getProxyHost();
        //代理的端口
        Integer proxyPort = properties.getProxyPort();
        //发送请求
        JSONObject responseJSON = OpenAIConnecter.sendPost(url, apikey, requestBody, proxyHost, proxyPort);

        String answer = responseJSON.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
        return answer;
    }

}
