package com.zzmr.fgback.controller.app;

import com.zzmr.fgback.bean.Message;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zzmr
 * @create 2024-03-31 21:55
 */
@RestController
@CrossOrigin
@Slf4j
@Api(tags = "消息相关接口")
@RequestMapping("/app/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/get")
    @ApiOperation("查询消息列表")
    public Result get() {
        List<Message> messageList = messageService.get();
        return Result.success(messageList);
    }

}
