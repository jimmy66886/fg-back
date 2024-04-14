package com.zzmr.fgback.mapper;

import com.zzmr.fgback.bean.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zzmr
 * @since 2024-04-14
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 群发消息
     *
     * @param messageList
     */
    void insertAll(@Param("messageList") List<Message> messageList);
}
