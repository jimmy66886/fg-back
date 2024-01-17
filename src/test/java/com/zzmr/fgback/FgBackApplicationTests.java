package com.zzmr.fgback;

import com.zzmr.fgback.bean.User;
import com.zzmr.fgback.mapper.UserMapper;
import com.zzmr.fgback.service.UserService;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
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

    @Test
    void testBeanUtils() {
        Test1 t1 = new Test1();
        Test2 t2 = new Test2();
        /**
         * 如果要拷贝的某个字段名一样但是类型不一样，则拷贝失败，且不会报错
         */
        BeanUtils.copyProperties(t2, t1);
        System.out.println(t1);
    }

}

@Data
class Test1 {
    private String id;
}

@Data
class Test2 {
    // private String id = 10L;
    private String id = "10";
}

