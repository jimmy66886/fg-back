package com.zzmr.fgback.mapper;

import com.zzmr.fgback.bean.Favorite;
import com.zzmr.fgback.bean.Favorites;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzmr.fgback.vo.FavoritesVo;
import com.zzmr.fgback.vo.RecipeBasicVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zzmr
 * @since 2024-01-24
 */
@Mapper
public interface FavoritesMapper extends BaseMapper<Favorites> {

    /**
     * 获取用户全部自定义收藏夹以及对应的数量（不包括封面图）
     *
     * @return
     */
    List<FavoritesVo> getAllFavorites(@Param("userId") Long userId);

    /**
     * 根据收藏夹id获取收藏夹封面
     *
     * @param favoritesId
     * @return
     */
    String getCoverImg(@Param("favoritesId") Long favoritesId);

    /**
     * 根据收藏实体  条件搜索菜谱
     * userId和favoritesId
     *
     * @return
     */
    List<RecipeBasicVo> getRecipeList(@Param("favorite") Favorite favorite);
}
