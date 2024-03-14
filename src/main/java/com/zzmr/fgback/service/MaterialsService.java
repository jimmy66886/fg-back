package com.zzmr.fgback.service;

import com.zzmr.fgback.bean.Materials;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzmr.fgback.vo.MaterialsVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-23
 */
public interface MaterialsService extends IService<Materials> {

    /**
     * 获取所有用料
     *
     * @return
     */
    List<MaterialsVo> getAll();
}
