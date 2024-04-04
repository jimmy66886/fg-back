package com.zzmr.fgback.service;

import com.zzmr.fgback.bean.Favorites;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzmr.fgback.vo.FavoritesVo;
import com.zzmr.fgback.vo.RecipeBasicVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-24
 */
public interface FavoritesService extends IService<Favorites> {

    /**
     * 获取用户所有的收藏夹
     *
     * @return
     */
    List<FavoritesVo> getAllFavorites();

    /**
     * 添加一个收藏夹
     *
     * @param favorites
     */
    Long insert(Favorites favorites);

    /**
     * 获取收藏夹的菜谱列表
     *
     * @param favoritesId
     * @return
     */
    List<RecipeBasicVo> getRecipeList(Long favoritesId);

    /**
     * 根据收藏夹id获取收藏夹信息
     *
     * @param favoritesId
     * @return
     */
    Favorites getFavoritesInfo(Long favoritesId);

    /**
     * 根据收藏夹id删除菜谱收藏夹
     *
     * @param favoritesId
     */
    void deleteById(Long favoritesId);
}
