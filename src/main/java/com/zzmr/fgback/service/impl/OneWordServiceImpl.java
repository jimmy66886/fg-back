package com.zzmr.fgback.service.impl;

import com.zzmr.fgback.bean.OneWord;
import com.zzmr.fgback.mapper.OneWordMapper;
import com.zzmr.fgback.service.OneWordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzmr
 * @since 2024-02-16
 */
@Service
public class OneWordServiceImpl extends ServiceImpl<OneWordMapper, OneWord> implements OneWordService {

    @Autowired
    private OneWordMapper oneWordMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public OneWord get() {
        Random random = new Random();
        /**
         * 数据库中只有101条记录,写死就行了,后期也不会添加
         */
        int i = random.nextInt(101);
        OneWord oneWordCache = redisUtils.getJsonToBean("oneWord:" + i, OneWord.class);
        if (oneWordCache != null) {
            // 缓存中有数据
            return oneWordCache;
        }
        // 在db中查
        OneWord oneWord = oneWordMapper.selectById(i);
        redisUtils.setBean("oneWord:" + i, oneWord);
        return oneWord;
    }
}
