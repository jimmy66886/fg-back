package com.zzmr.fgback.service;

import com.zzmr.fgback.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzmr.fgback.dto.UserLoginDto;
import com.zzmr.fgback.dto.UserRegisterDto;
import com.zzmr.fgback.dto.WxLoginDto;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 负责更新昵称，个人签名这两项
     *
     * @param user
     */
    void updateBasic(User user);

    /**
     * 更新用户头像
     *
     * @param avatarImg
     * @return
     */
    String updateAvatar(MultipartFile avatarImg);

    /**
     * 获取用户信息
     * @return
     */
    User get();
}
