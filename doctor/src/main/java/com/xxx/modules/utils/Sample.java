package com.xxx.modules.utils;


import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * 需要添加依赖
 * <!-- https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp -->
 * <dependency>
 *     <groupId>com.squareup.okhttp3</groupId>
 *     <artifactId>okhttp</artifactId>
 *     <version>4.12.0</version>
 * </dependency>
 */

public class Sample {
    public static final String API_KEY = "";
    public static final String SECRET_KEY = "";

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)//设置连接超时时间
            .readTimeout(60, TimeUnit.SECONDS)//设置读取超时时间
            .build();


    public static void main(String []args) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("role","user");
        jsonObject.put("content","刑法相关介绍");
        jsonArray.put(jsonObject);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("messages",jsonArray);
        System.out.println(jsonObject1.toString());
        RequestBody body = RequestBody.create(mediaType, jsonObject1.toString());
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions_pro?access_token=" + getAccessToken())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        System.out.println(response.body().string());

    }

    public static String getAiAnswer(String question){
        try {
            MediaType mediaType = MediaType.parse("application/json");
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("role","user");
            jsonObject.put("content",question);
            jsonArray.put(jsonObject);

            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("messages",jsonArray);
            System.out.println(jsonObject1.toString());
            RequestBody body = RequestBody.create(mediaType, jsonObject1.toString());
            Request request = new Request.Builder()
                    .url("https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions_pro?access_token=" + getAccessToken())
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = HTTP_CLIENT.newCall(request).execute();
            return response.body().string();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }



    /**
     * 从用户的AK，SK生成鉴权签名（Access Token）
     *
     * @return 鉴权签名（Access Token）
     * @throws IOException IO异常
     */
    static String getAccessToken() throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&client_id=" + API_KEY
                + "&client_secret=" + SECRET_KEY);
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/oauth/2.0/token")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        return new JSONObject(response.body().string()).getString("access_token");
    }

}