package com.zzmr.fgback.service.impl;

import com.zzmr.fgback.bean.User;
import com.zzmr.fgback.mapper.UserMapper;
import com.zzmr.fgback.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
