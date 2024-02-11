package com.zzmr.fgback.controller.admin;

import com.zzmr.fgback.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author zzmr
 * @create 2024-02-03 11:54
 */
@RestController
@CrossOrigin
@Slf4j
@Api(tags = "管理员相关接口")
@RequestMapping("/admin/user")
public class AdminController {

    @PostMapping("/login")
    @ApiOperation("管理员登陆")
    public Result login() {
        log.info("登录成功");
        return Result.success();
    }

}
