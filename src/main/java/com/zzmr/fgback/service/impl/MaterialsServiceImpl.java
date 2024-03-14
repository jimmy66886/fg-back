package com.zzmr.fgback.service.impl;

import com.zzmr.fgback.bean.Materials;
import com.zzmr.fgback.mapper.MaterialsMapper;
import com.zzmr.fgback.service.MaterialsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.vo.MaterialsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-23
 */
@Service
public class MaterialsServiceImpl extends ServiceImpl<MaterialsMapper, Materials> implements MaterialsService {

    @Autowired
    private MaterialsMapper materialsMapper;

    /**
     * 获取所有用料
     *
     * @return
     */
    @Override
    public List<MaterialsVo> getAll() {
        return materialsMapper.getAll();
    }
}
