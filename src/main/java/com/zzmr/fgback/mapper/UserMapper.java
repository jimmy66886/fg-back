package com.zzmr.fgback.mapper;

import com.zzmr.fgback.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
