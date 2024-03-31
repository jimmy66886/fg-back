package com.zzmr.fgback.mapper;

import com.zzmr.fgback.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzmr.fgback.vo.SearchUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 根据用户名查询用户列表，同时查询出是否关注
     * 所以需要查询用户的id
     *
     * @param userId
     * @param nickName
     * @return
     */
    List<SearchUserVo> searchUserVo(@Param("userId") Long userId, @Param("nickName") String nickName);
}
