package com.zzmr.fgback;

import com.zzmr.fgback.bean.User;
import com.zzmr.fgback.mapper.UserMapper;
import com.zzmr.fgback.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
class FgBackApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println(redisTemplate);
        System.out.println(userService);
        System.out.println(userMapper);
        User user = userMapper.getUserByAccount("17513571210");
        System.out.println(user);

        // redisTemplate.opsForValue().set("testKey", 1);
        System.out.println(redisTemplate.opsForValue().get("testKey"));
    }

}
