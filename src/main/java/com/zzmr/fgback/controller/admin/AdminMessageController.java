package com.zzmr.fgback.controller.admin;

import com.zzmr.fgback.bean.Message;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zzmr
 * @create 2024-04-14 19:37
 */
@RestController
@CrossOrigin
@Slf4j
@Api(tags = "管理端发送消息相关接口")
@RequestMapping("/admin/message")
public class AdminMessageController {

    @Autowired
    private MessageService messageService;

    @ApiOperation("管理员单独发送消息")
    @PostMapping("/send")
    public Result send(@RequestBody Message message) {
        messageService.save(message);
        return Result.success();
    }

    @ApiOperation("管理员群发消息")
    @PostMapping("sendAll")
    public Result sendAll(@RequestBody Message message) {
        messageService.sendAll(message);
        return Result.success();
    }

}
