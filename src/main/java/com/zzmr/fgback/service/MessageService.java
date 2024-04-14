package com.zzmr.fgback.service;

import com.zzmr.fgback.bean.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zzmr
 * @since 2024-04-14
 */
public interface MessageService extends IService<Message> {

    /**
     * 获取消息列表
     *
     * @return
     */
    List<Message> get();

    /**
     * 群发消息
     *
     * @param message
     */
    void sendAll(Message message);
}
