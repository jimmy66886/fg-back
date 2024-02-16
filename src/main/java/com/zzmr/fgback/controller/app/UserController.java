package com.zzmr.fgback.controller.app;


import com.zzmr.fgback.bean.User;
import com.zzmr.fgback.constant.JwtClaimsConstant;
import com.zzmr.fgback.dto.UserLoginDto;
import com.zzmr.fgback.dto.UserRegisterDto;
import com.zzmr.fgback.properties.JwtProperties;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.UserService;
import com.zzmr.fgback.util.JwtUtils;
import com.zzmr.fgback.vo.UserLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
@RestController
@CrossOrigin
@Slf4j
@Api(tags = "用户相关接口")
@RequestMapping("/app/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 只保留邮箱登录，手机号登录删除
     *
     * @param userLoginDto
     * @return
     */
    @ApiOperation("用户登录-邮箱密码登录或邮箱验证码登录")
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDto userLoginDto) {
        User user = userService.login(userLoginDto);

        // 登录成功，生成token

        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getUserId());

        String token = JwtUtils.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);
        UserLoginVo userLoginVo = new UserLoginVo();
        BeanUtils.copyProperties(user, userLoginVo);
        userLoginVo.setToken(token);
        return Result.success(userLoginVo);
    }

    @ApiOperation("获取验证码")
    @PostMapping("/getCode")
    public Result getCode(@RequestBody UserLoginDto userLoginDto) {
        // 前端只需要传来一个email,后端返回一个code
        String code = userService.getCode(userLoginDto);
        return Result.success();
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDto userRegisterDto) {
        userService.register(userRegisterDto);
        return Result.success();
    }

}

