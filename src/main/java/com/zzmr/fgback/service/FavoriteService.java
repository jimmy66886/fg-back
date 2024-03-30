package com.zzmr.fgback.service;

import com.zzmr.fgback.bean.Favorite;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzmr.fgback.bean.Favorites;
import com.zzmr.fgback.dto.PageFavoriteDto;
import com.zzmr.fgback.result.PageResult;
import com.zzmr.fgback.vo.FavoritesVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
public interface FavoriteService extends IService<Favorite> {


    /**
     * 添加一个菜谱收藏
     *
     * @param recipeId
     */
    void insert(Long recipeId);

    /**
     * 删除一个菜谱收藏
     *
     * @param recipeId
     */
    void delete(Long recipeId);

    /**
     * 查询用户是否收藏过菜谱
     *
     * @return
     */
    Boolean getFavorited(Long recipeId);

    /**
     * 分页查询用户的全部收藏
     *
     * @param pageFavoriteDto
     * @return
     */
    PageResult getAll(PageFavoriteDto pageFavoriteDto);

    /**
     * 添加到指定自定义收藏夹
     *
     * @param favorite
     */
    void addTo(Favorite favorite);

    /**
     * 根据菜谱ids删除菜谱收藏
     *
     * @param recipeIds
     */
    void deleteBatch(List<Long> recipeIds);
}
