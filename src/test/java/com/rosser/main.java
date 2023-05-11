package com.rosser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class main {
    public static void main(String[] args) {
        // 设置 API 密钥
        String apiKey = "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";

        // 设置提示
        String prompt = "What is the capital of France?";
        // 设置代理
        HttpHost proxy = new HttpHost("localhost", 7890);
        // 创建 HttpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            // 创建 HttpPost 对象
            HttpPost httpPost = new HttpPost("https://api.openai.com/v1/chat/completions");
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
            // 设置请求配置（包括代理）
            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();
            httpPost.setConfig(requestConfig);
            // 设置请求体
            JSONObject requestBody = new JSONObject();
            JSONArray messages = new JSONArray();
            JSONObject message1 = new JSONObject();
            message1.put("role","user");
            message1.put("content","Hello!");
            //requestBody.put("prompt", prompt);
            requestBody.put("model","gpt-3.5-turbo");
            messages.add(message1);
            requestBody.put("messages",messages);
            httpPost.setEntity(new StringEntity(requestBody.toJSONString()));

            // 发送请求并获取响应
            CloseableHttpResponse response = httpClient.execute(httpPost);

            // 解析响应
            String responseBody = EntityUtils.toString(response.getEntity());
            JSONObject jsonResponse = JSON.parseObject(responseBody);
            String answer = jsonResponse.getJSONArray("choices").getJSONObject(0).getString("text");

            // 输出结果
            System.out.println(answer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
