package com.zzmr.fgback.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @zzmr 2024年3月5日 15点46分
 */
@Component
@Data
@ConfigurationProperties(prefix = "bce")
public class BceProperties {

    private String apiKey;

    private String secretKey;

}
