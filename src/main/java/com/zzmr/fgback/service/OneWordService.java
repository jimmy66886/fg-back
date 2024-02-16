package com.zzmr.fgback.service;

import com.zzmr.fgback.bean.OneWord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzmr
 * @since 2024-02-16
 */
public interface OneWordService extends IService<OneWord> {

    /**
     * 获取一言
     * @return
     */
    OneWord get();
}
