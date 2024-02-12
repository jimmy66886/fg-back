package com.zzmr.fgback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzmr.fgback.bean.User;
import com.zzmr.fgback.dto.UserLoginDto;
import com.zzmr.fgback.dto.UserRegisterDto;
import com.zzmr.fgback.exception.BaseException;
import com.zzmr.fgback.mapper.UserMapper;
import com.zzmr.fgback.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.util.MailUtils;
import com.zzmr.fgback.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private MailUtils mailUtils;


    /**
     * 验证码登录,要先判断这个邮箱是否存在
     *
     * @param userLoginDto
     * @return
     */
    @Override
    public User login(UserLoginDto userLoginDto) {
        // 修改成可以用手机号或者邮箱登录
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getEmail, userLoginDto.getEmail())
        );
        if (user == null) {
            // 账号不存在
            throw new BaseException("账号不存在");
        }

        // 密码不为空,说明是邮箱密码登录
        if (userLoginDto.getPassword() != "") {
            if (!user.getPassword().equals(userLoginDto.getPassword())) {
                // 密码错误
                throw new BaseException("密码错误");
            }
            return user;
        } else {
            // 邮箱验证码登录
            String code = redisUtils.getStr(userLoginDto.getEmail());
            log.info("要登录的邮箱: {}, 前端传来的验证码:{}, 缓存中的验证码: {}", userLoginDto.getEmail(), userLoginDto.getCode(), code);
            if (userLoginDto.getCode().equals(code)) {
                return user;
            }
            throw new BaseException("验证码错误");
        }
    }

    /**
     * 获取验证码
     *
     * @return
     */
    @Override
    public String getCode(UserLoginDto userLoginDto) {

        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getEmail, userLoginDto.getEmail()));
        if (userLoginDto.getIsRegister()) {
            // 注册用的验证码
            if (user != null) {
                // 用户存在,则返回异常
                throw new BaseException("该邮箱已被注册");
            }
            String code = generateVerificationCode();
            log.info("注册邮箱: {},验证码为: {}", userLoginDto.getEmail(), code);
            // 将邮箱和验证码作为键值对存入redis,并设置5分钟有效期 300s
            redisUtils.set(userLoginDto.getEmail(), code, 300L);
            // 发送邮件
            mailUtils.sendMail(userLoginDto.getEmail(), "有食做注册", "您的[有食做]注册验证码为" + code);
            return code;
        } else {
            // 登录用验证码
            if (user == null) {
                // 账号不存在
                throw new BaseException("账号不存在");
            }
            // 正常登录用验证码逻辑
            String code = generateVerificationCode();
            log.info("登录邮箱: {},验证码为: {}", userLoginDto.getEmail(), code);
            // 将邮箱和验证码作为键值对存入redis,并设置5分钟有效期 300s
            redisUtils.set(userLoginDto.getEmail(), code, 300L);
            mailUtils.sendMail(userLoginDto.getEmail(), "有食做登录", "您的[有食做]登录验证码为" + code);
            return code;
        }
    }

    /**
     * 用户注册
     *
     * @param userRegisterDto
     */
    @Override
    public void register(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getCode().equals(redisUtils.getStr(userRegisterDto.getEmail()))) {
            throw new BaseException("验证码错误");
        }
        User user = new User();
        BeanUtils.copyProperties(userRegisterDto, user);
        userMapper.insert(user);
    }

    // 生成随机的四位数字验证码
    public String generateVerificationCode() {
        // 设置验证码长度为4
        int codeLength = 4;
        // 验证码字符源
        String sourceNumbers = "0123456789";
        // 生成随机对象
        Random random = new Random();
        // 用于存储生成的验证码
        StringBuilder verificationCode = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            // 从字符源中随机获取一个字符，并追加到验证码中
            int index = random.nextInt(sourceNumbers.length());
            verificationCode.append(sourceNumbers.charAt(index));
        }
        // 返回生成的验证码
        return verificationCode.toString();
    }
}