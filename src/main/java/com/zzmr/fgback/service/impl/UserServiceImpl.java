package com.zzmr.fgback.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzmr.fgback.bean.User;
import com.zzmr.fgback.constant.OrderConstant;
import com.zzmr.fgback.dto.*;
import com.zzmr.fgback.exception.BaseException;
import com.zzmr.fgback.mapper.UserMapper;
import com.zzmr.fgback.properties.WechatProperties;
import com.zzmr.fgback.result.PageResult;
import com.zzmr.fgback.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.util.*;
import com.zzmr.fgback.vo.SearchUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private WechatProperties wechatProperties;

    @Autowired
    private MinioUtils minioUtils;

    private static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";


    /**
     * 验证码登录,要先判断这个邮箱是否存在
     *
     * @param userLoginDto
     * @return
     */
    @Override
    public User loginByCode(UserLoginDto userLoginDto) {
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

    @Override
    public User wxLogin(WxLoginDto wxLoginDto) {
        String openid = getString(wxLoginDto);
        // 判断其openid是否为空
        if (openid == null) {
            throw new BaseException("登陆失败");
        }
        log.info("用户的openid为:{}", openid);

        // 根据openid在数据库中查询,如果存在该openid则表示用户已注册,反之自动为用户进行注册
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getOpenid, openid));
        if (user == null) {
            // 新用户,则进行注册 设置状态为0
            // 并设置默认的用户名和密码,用户名就用“微信用户+xxxx”四位数字表示
            user = User.builder().openid(openid).nickName("微信用户" + generateVerificationCode()).avatarUrl("http://47" +
                    ".109.139.173:9000/food.guide/%E9%BB%98%E8%AE%A4%E5%A4%B4%E5%83%8F.jpg").status(Boolean.TRUE).build();
            userMapper.insert(user);
        }

        // 2024年4月8日 09点48分
        // 加上判断用户是否为启用状态  1 启用 0 禁用
        if (!user.getStatus()) {
            throw new BaseException("该用户已被封禁!");
        }

        // 已注册,则直接返回
        return user;
    }

    /**
     * 负责更新昵称，个人签名这两项
     *
     * @param user
     */
    @Override
    public void updateBasic(User user) {
        user.setUserId(ContextUtils.getCurrentId());
        userMapper.updateById(user);
    }

    /**
     * 更新用户头像
     *
     * @param avatarImg
     * @return
     */
    @Override
    public String updateAvatar(MultipartFile avatarImg) {
        String imgUrl = minioUtils.upload(avatarImg);
        // 将新头像插入数据库
        User user = new User();
        user.setAvatarUrl(imgUrl);
        user.setUserId(ContextUtils.getCurrentId());
        userMapper.updateById(user);
        return imgUrl;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @Override
    public User get() {
        Long userId = ContextUtils.getCurrentId();
        return userMapper.selectById(userId);
    }

    /**
     * 根据用户名查询用户信息
     * 该信息中应包含是否关注该用户
     *
     * @param user
     * @return
     */
    @Override
    public List<SearchUserVo> search(User user) {

        // 需要用到查询用户的id
        Long userId = ContextUtils.getCurrentId();
        List<SearchUserVo> searchUserVoList = userMapper.searchUserVo(userId, user.getNickName());
        return searchUserVoList;
    }

    /**
     * 管理员登录
     *
     * @param adminLoginDto
     * @return
     */
    @Override
    public User login(AdminLoginDto adminLoginDto) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getAccount,
                adminLoginDto.getAccount()));
        if (user == null) {
            throw new BaseException("账号不存在");
        }

        if (!adminLoginDto.getPassword().equals(user.getPassword())) {
            throw new BaseException("账号或密码错误");
        }

        return user;
    }

    /**
     * 获取用户信息
     *
     * @param searchUserDto
     * @return
     */
    @Override
    public PageResult getUserList(SearchUserDto searchUserDto) {
        PageHelper.startPage(searchUserDto.getPage(), searchUserDto.getPageSize());
        Page<User> userList =
                (Page<User>) userMapper.selectList(new LambdaQueryWrapper<User>().like(User::getNickName,
                        searchUserDto.getNickName()));
        return new PageResult(userList.getTotal(), userList.getResult());
    }

    /**
     * 改变用户状态
     *
     * @param user
     */
    @Override
    public void changeStatus(User user) {
        userMapper.updateById(user);
    }

    /**
     * 将根据userLoginDTO获取openId的片段抽取成一个方法
     *
     * @return
     */
    private String getString(WxLoginDto wxLoginDto) {
        // 1. 调用微信接口服务 获取openId
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", wechatProperties.getAppid());
        paramMap.put("secret", wechatProperties.getSecret());
        paramMap.put("js_code", wxLoginDto.getCode());
        paramMap.put("grant_type", "authorization_code");

        String json = HttpClientUtil.doGet(WX_LOGIN, paramMap);

        JSONObject jsonObject = JSON.parseObject(json);
        String openId = jsonObject.getString("openid");
        return openId;
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