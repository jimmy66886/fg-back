package com.zzmr.fgback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzmr.fgback.bean.User;
import com.zzmr.fgback.dto.UserLoginDto;
import com.zzmr.fgback.exception.BaseException;
import com.zzmr.fgback.mapper.UserMapper;
import com.zzmr.fgback.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User login(UserLoginDto userLoginDto) {

        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getAccount, userLoginDto.getAccount())
        );
        if (user == null) {
            // 账号不存在
            throw new BaseException("账号不存在");
        }
        if (!user.getPassword().equals(userLoginDto.getPassword())) {
            // 密码错误
            throw new BaseException("密码错误");
        }

        return user;
    }
}
