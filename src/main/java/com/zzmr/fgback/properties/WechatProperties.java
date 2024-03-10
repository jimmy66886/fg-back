package com.zzmr.fgback.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zzmr
 * @create 2024-03-10 22:31
 */
@Component
@Data
@ConfigurationProperties(prefix = "fg.wechat")
public class WechatProperties {

    // 小程序appid
    private String appid;

    // 小程序密钥
    private String secret;

}
