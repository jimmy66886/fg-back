package com.zzmr.fgback.service;

import com.zzmr.fgback.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzmr.fgback.dto.UserLoginDto;

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
}
