package com.zzmr.fgback.util;

/**
 * @author zzmr
 * @create 2024-01-31 13:56
 * 上下文工具类
 * 将用户的请求头中的token中的id解析出，然后在需要的地方使用
 */
public class ContextUtils {

    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static Long getCurrentId() {
        return threadLocal.get();
    }

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }

}
