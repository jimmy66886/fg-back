
package com.zzmr.fgback.util;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzmr.fgback.properties.BceProperties;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * 菜品识别
 */
@Component
public class DishUtils {

    private static BceProperties bceProperties;

    @Autowired
    public DishUtils(BceProperties bceProperties) {
        DishUtils.bceProperties = bceProperties;
    }

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     * 直接调用这个方法,方法返回最接近的菜品名
     * 用的时候直接将图片链接传进来就行
     */
    public static String recognition(String filePath) throws IOException {
        String accessToken = getAccessToken();
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v2/dish";
        try {
            // 本地文件路径
            byte[] imgData = FileUtil.readUrlImageAsBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam + "&top_num=" + 5;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String result = HttpUtil.post(url, accessToken, param);
            // 解析result，输出最接近的那个name,也就是数组第一项
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(result);
            JsonNode resultArray = jsonNode.get("result");
            String mostProbabilityName = resultArray.get(0).get("name").asText();
            return mostProbabilityName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取鉴权token
     *
     * @return
     * @throws IOException
     */
    public static String getAccessToken() throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/oauth/2.0/token?client_id=" + bceProperties.getApiKey() +
                        "&client_secret=" + bceProperties.getSecretKey() + "&grant_type=client_credentials")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        // 这个请求体只能读取一次，读取完后会自动关闭
        // System.out.println(response.body().string());
        // 解析出access_token
        ObjectMapper objectMapper = new ObjectMapper();
        // 将json字符串解析为jsonNode对象
        JsonNode jsonNode = objectMapper.readTree(response.body().string());
        String accessToken = jsonNode.get("access_token").asText();
        return accessToken;
    }
}