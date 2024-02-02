package com.zzmr.fgback.mapper;

import com.zzmr.fgback.bean.Favorite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzmr.fgback.vo.FavoriteVo;
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
public interface FavoriteMapper extends BaseMapper<Favorite> {

    /**
     * 获取用户的全部收藏
     *
     * @param userId
     * @return
     */
    List<FavoriteVo> getFavoriteVo(@Param("userId") Long userId);

    /**
     * 更新收藏信息
     * 用于将收藏存放进指定收藏夹
     * @param favorite
     */
    void updateOne(@Param("favorite") Favorite favorite);
}
