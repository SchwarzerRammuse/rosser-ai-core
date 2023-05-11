package com.rosser.connect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

/**
 * 和openai进行直接交互的类，这里只需要写http相关的代码
 */
public class OpenAIConnecter {

    public static JSONObject sendPost(String apiUrl, String apiKey, JSONObject requestbody){
       return sendPost(apiUrl,apiKey,requestbody,null,null);
    }


    public static JSONObject sendPost(String apiUrl, String apiKey, JSONObject requestBody, String proxyHostName, Integer port){
        //默认代理
        HttpHost proxy = new HttpHost("localhost", 7890);
        if(null!=proxyHostName&&!"".equals(proxyHostName)){
            if(null!=port){
                // 设置代理
                proxy = new HttpHost(proxyHostName, port);
            }
        }
        // 创建 HttpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            // 创建 HttpPost 对象
            //HttpPost httpPost = new HttpPost("https://api.openai.com/v1/chat/completions");
            HttpPost httpPost = new HttpPost(apiUrl);
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
            // 设置请求配置（包括代理）
            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();
            httpPost.setConfig(requestConfig);
            HttpEntity entity = httpPost.getEntity();
            httpPost.setEntity(new StringEntity(requestBody.toJSONString(),"UTF-8"));

            // 发送请求并获取响应
            CloseableHttpResponse response = httpClient.execute(httpPost);

            // 解析响应
            String responseBody = EntityUtils.toString(response.getEntity());
            JSONObject jsonResponse = JSON.parseObject(responseBody);
            return jsonResponse;
            //


            // 输出结果
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
