package com.zzmr.fgback.service;

import com.zzmr.fgback.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzmr.fgback.dto.UserLoginDto;
import com.zzmr.fgback.dto.UserRegisterDto;
import com.zzmr.fgback.dto.WxLoginDto;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     *
     * @param userLoginDto
     * @return
     */
    User login(UserLoginDto userLoginDto);

    /**
     * 获取验证码
     *
     * @return
     */
    String getCode(UserLoginDto userLoginDto);

    /**
     * 用户注册
     *
     * @param userRegisterDto
     */
    void register(UserRegisterDto userRegisterDto);

    /**
     * 微信登陆
     *
     * @param wxLoginDto
     * @return
     */
    User wxLogin(WxLoginDto wxLoginDto);
}
