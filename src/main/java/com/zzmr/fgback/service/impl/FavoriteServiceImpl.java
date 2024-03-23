package com.zzmr.fgback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzmr.fgback.bean.Favorite;
import com.zzmr.fgback.bean.Favorites;
import com.zzmr.fgback.constant.OrderConstant;
import com.zzmr.fgback.dto.PageFavoriteDto;
import com.zzmr.fgback.mapper.FavoriteMapper;
import com.zzmr.fgback.mapper.FavoritesMapper;
import com.zzmr.fgback.result.PageResult;
import com.zzmr.fgback.service.FavoriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.util.ContextUtils;
import com.zzmr.fgback.vo.FavoriteVo;
import com.zzmr.fgback.vo.FavoritesVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private FavoritesMapper favoritesMapper;

    /**
     * 添加一个菜谱收藏
     *
     * @param recipeId
     */
    @Override
    public void insert(Long recipeId) {
        Favorite favorite = Favorite.builder().recipeId(recipeId).userId(ContextUtils.getCurrentId()).build();
        favoriteMapper.insert(favorite);
    }

    /**
     * 删除一个菜谱收藏
     *
     * @param recipeId
     */
    @Override
    public void delete(Long recipeId) {
        favoriteMapper.delete(new LambdaQueryWrapper<Favorite>()
                .eq(Favorite::getUserId, ContextUtils.getCurrentId())
                .eq(Favorite::getRecipeId, recipeId)
        );
    }

    /**
     * 查询用户是否收藏过菜谱
     *
     * @param recipeId
     * @return
     */
    @Override
    public Boolean getFavorited(Long recipeId) {
        Integer value = favoriteMapper.selectCount(new LambdaQueryWrapper<Favorite>()
                .eq(Favorite::getRecipeId, recipeId)
                .eq(Favorite::getUserId, ContextUtils.getCurrentId())
        );
        if (value > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * 分页查询用户的全部收藏
     *
     * @param pageFavoriteDto
     * @return
     */
    @Override
    public PageResult getAll(PageFavoriteDto pageFavoriteDto) {
        PageHelper.startPage(pageFavoriteDto.getPage(), pageFavoriteDto.getPageSize());
        Favorite favorite = new Favorite();
        favorite.setUserId(ContextUtils.getCurrentId());
        Page page = (Page) favoritesMapper.getRecipeList(favorite);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void addTo(Favorite favorite) {
        favorite.setUserId(ContextUtils.getCurrentId());
        // 传进来的有收藏夹id和菜谱id
        favoriteMapper.insert(favorite);
    }
}
