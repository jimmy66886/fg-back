package com.zzmr.fgback;

import cn.hutool.json.JSONUtil;
import com.zzmr.fgback.bean.Recipe;
import com.zzmr.fgback.mapper.RecipeMapper;
import com.zzmr.fgback.mapper.UserMapper;
import com.zzmr.fgback.properties.JwtProperties;
import com.zzmr.fgback.service.UserService;
import com.zzmr.fgback.util.JwtUtils;
import com.zzmr.fgback.util.RedisUtils;
import io.jsonwebtoken.Claims;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class FgBackApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RecipeMapper recipeMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private JwtProperties jwtProperties;

    @Test
    void testRedis() {
        Recipe recipe = new Recipe();
        recipe.setTitle("测试redis");
        redisUtils.setBean("o1", recipe);
        System.out.println(redisUtils.getStr("k1"));
        Recipe recipe1 = redisUtils.getJsonToBean("o1", Recipe.class);
        System.out.println(recipe1.getTitle());
    }

    @Test
    void testReidsList() {
        List<String> stringList = Arrays.asList("1", "2", "3");
        redisUtils.setBean("l1", stringList);
        String jsonStringList = redisUtils.getStr("l1");
        List<String> result = JSONUtil.parseArray(jsonStringList).toList(String.class);
        System.out.println(result);
    }

    @Test
    void testRedisUtilsList() {
        List<String> stringList = Arrays.asList("4", "2", "3");
        redisUtils.setList("List1", stringList);

        List<String> retrievedList = redisUtils.getList("List1", String.class);
        System.out.println(retrievedList);
    }

    @Test
    void cleanCache(){
        redisUtils.cleanCache("recipe_*");
    }

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("testKey", 1);
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

    @Test
    public void testStr() {
        String fileName = "test.jpg";
        System.out.println(fileName.lastIndexOf("."));
        System.out.println(fileName.substring(4));
    }

    /**
     * 测试token工具类
     */
    @Test
    public void testToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", 1);
        String token = JwtUtils.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
        System.out.println(token);
        Claims claims1 = JwtUtils.parseJWT(jwtProperties.getUserSecretKey(), token);
        System.out.println(claims1.get("userId"));
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


