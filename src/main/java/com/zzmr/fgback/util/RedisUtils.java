package com.zzmr.fgback.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 获取
     */
    public String getStr(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取Long
     */
    public Long getLong(String key) {
        try {
            return Long.parseLong(redisTemplate.opsForValue().get(key));
        } catch (Exception e) {
            return null;
        }
    }

    public BigDecimal getBigDecimal(String key) {
        return new BigDecimal(redisTemplate.opsForValue().get(key));
    }

    /**
     * 数值增加
     */
    public Long increment(String key, Long add) {
        String str = getStr(key);
        if (!NumberUtil.isNumber(str)) {
            return null;
        }
        return redisTemplate.opsForValue().increment(key, add);
    }

    /**
     * 当字符串为Json格式时, 可转换对象 >>> 会返回空
     */
    public <T> T getJsonToBean(String key, Class<T> beanClass) {
        String value = redisTemplate.opsForValue().get(key);
        if (JSONUtil.isJson(value)) {
            return JSONUtil.toBean(value, beanClass);
        }
        return null;
    }

    /**
     * 存值 key, value
     */
    public void set(String key, String value) {
        if (StrUtil.isNotBlank(key) && StrUtil.isNotBlank(value)) {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    /**
     * 存值 key, value, time  >>>  时间单位: 秒
     */
    public void set(String key, String value, Long time) {
        if (StrUtil.isNotBlank(key) && StrUtil.isNotBlank(value)) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 存值 >>> JSON
     */
    public <T> void setBean(String key, T value) {
        if (StrUtil.isNotBlank(key) && !BeanUtil.isEmpty(value)) {
            set(key, JSONUtil.toJsonStr(value));
        }
    }

    /**
     * 存值 >>> JSON 时间单位: 秒数
     */
    public <T> void setBean(String key, T value, Long time) {
        if (StrUtil.isNotBlank(key) && BeanUtil.isEmpty(value)) {
            set(key, JSONUtil.toJsonStr(value), time);
        }
    }

    /**
     * 存储集合
     *
     * @param key   键
     * @param list  集合对象
     * @param <T>   集合元素类型
     */
    public <T> void setList(String key, List<T> list) {
        if (key != null && list != null) {
            redisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(list));
        }
    }

    /**
     * 获取集合
     *
     * @param key      键
     * @param classOfT 集合元素类型
     * @param <T>      集合元素类型
     * @return         集合对象
     */
    public <T> List<T> getList(String key, Class<T> classOfT) {
        String jsonString = redisTemplate.opsForValue().get(key);
        if (jsonString != null) {
            JSONArray jsonArray = JSONUtil.parseArray(jsonString);
            return jsonArray.toList(classOfT);
        }
        return null;
    }
}