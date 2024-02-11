package com.zzmr.fgback.mapper;

import com.zzmr.fgback.bean.Followers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzmr.fgback.vo.FollowersVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zzmr
 * @since 2024-02-06
 */
@Mapper
public interface FollowersMapper extends BaseMapper<Followers> {

    /**
     * 获取关注列表
     *
     * @param currentId
     * @return
     */
    List<FollowersVo> getList(@Param("followerId") Long currentId);

    /**
     * 获取粉丝列表
     *
     * @param currentId
     * @return
     */
    List<FollowersVo> getFans(@Param("followingId") Long currentId);
}
