package com.zzmr.fgback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzmr.fgback.bean.Message;
import com.zzmr.fgback.bean.User;
import com.zzmr.fgback.mapper.MessageMapper;
import com.zzmr.fgback.mapper.UserMapper;
import com.zzmr.fgback.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.util.ContextUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzmr
 * @since 2024-04-14
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取消息列表
     *
     * @return
     */
    @Override
    public List<Message> get() {
        List<Message> messages = messageMapper.selectList(new LambdaQueryWrapper<Message>().eq(Message::getUserId,
                ContextUtils.getCurrentId()).orderByDesc(Message::getCreateTime));
        return messages;
    }

    /**
     * 群发消息
     *
     * @param message
     */
    @Override
    public void sendAll(Message message) {
        // 先获取所有的用户id
        List<User> users = userMapper.selectList(null);
        List<Message> messageList = new ArrayList<>();
        for (User user : users) {
            Message messageTmp = new Message();
            BeanUtils.copyProperties(message, messageTmp);
            messageTmp.setUserId(user.getUserId());
            messageList.add(messageTmp);
        }
        messageMapper.insertAll(messageList);
    }
}
