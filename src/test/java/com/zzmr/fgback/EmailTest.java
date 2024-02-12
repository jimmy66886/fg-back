package com.zzmr.fgback;

import com.zzmr.fgback.util.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zzmr
 * @create 2024-02-11 22:11
 */
@SpringBootTest
@Slf4j
public class EmailTest {

    @Autowired
    private MailUtils mailUtils;

    @Test
    public void testSend() {
        // mailUtils.sendMail("210398042@qq.com", "y17513571210@163.com", "210398042@qq.com", "测试邮件", "哈哈哈");
    }

}
