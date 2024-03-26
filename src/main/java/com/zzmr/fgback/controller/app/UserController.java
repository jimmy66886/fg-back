package com.zzmr.fgback.controller.app;


import com.zzmr.fgback.bean.User;
import com.zzmr.fgback.constant.JwtClaimsConstant;
import com.zzmr.fgback.dto.UserLoginDto;
import com.zzmr.fgback.dto.UserRegisterDto;
import com.zzmr.fgback.dto.WxLoginDto;
import com.zzmr.fgback.properties.JwtProperties;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.UserService;
import com.zzmr.fgback.util.JwtUtils;
import com.zzmr.fgback.util.MinioUtils;
import com.zzmr.fgback.vo.UserLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
     * 登录接口重写,注册接口删掉,只实现微信登录
     */
    @ApiOperation("微信登录")
    @PostMapping("/login")
    public Result login(@RequestBody WxLoginDto wxLoginDto) {
        log.info("微信用户登录,授权码:{}", wxLoginDto.getCode());
        User user = userService.wxLogin(wxLoginDto);
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

    /**
     * 获取用户信息
     */
    @ApiOperation("获取用户信息")
    @GetMapping("/get")
    public Result get() {
        User user = userService.get();
        return Result.success(user);
    }

    /**
     * 根据用户id获取用户信息
     */
    @ApiOperation("根据用户id获取用户信息")
    @GetMapping("/getById")
    public Result getById(@RequestParam Long userId) {
        User user = userService.getById(userId);
        return Result.success(user);
    }

    /**
     * 更新用户信息
     * 负责更新昵称，个人签名这两项
     */
    @ApiOperation("更新用户信息")
    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        userService.updateBasic(user);
        return Result.success();
    }

    /**
     * 更新用户头像
     */
    @ApiOperation("更新用户头像")
    @PostMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam(name = "avatarImg") MultipartFile avatarImg) {
        String newAvatarUrl = userService.updateAvatar(avatarImg);
        return Result.success(newAvatarUrl);
    }

    /**
     * 只保留邮箱登录，手机号登录删除
     *
     * @param userLoginDto
     * @return
     */
    /*@ApiOperation("用户登录-邮箱密码登录或邮箱验证码登录")
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
    }*/
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

