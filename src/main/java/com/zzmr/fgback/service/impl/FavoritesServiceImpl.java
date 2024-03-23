package com.zzmr.fgback.service.impl;

import com.zzmr.fgback.bean.Favorite;
import com.zzmr.fgback.bean.Favorites;
import com.zzmr.fgback.exception.BaseException;
import com.zzmr.fgback.mapper.FavoriteMapper;
import com.zzmr.fgback.mapper.FavoritesMapper;
import com.zzmr.fgback.mapper.RecipeMapper;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.FavoritesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.util.ContextUtils;
import com.zzmr.fgback.vo.FavoriteVo;
import com.zzmr.fgback.vo.FavoritesVo;
import com.zzmr.fgback.vo.RecipeBasicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-24
 */
@Service
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites> implements FavoritesService {

    @Autowired
    private FavoritesMapper favoritesMapper;

    @Autowired
    private FavoriteMapper favoriteMapper;


    /**
     * 获取用户所有的收藏夹
     * <p>
     * 用户有一个收藏夹是包含所有收藏的菜谱的
     * 要进行手动封装
     *
     * @return
     */
    @Override
    public List<FavoritesVo> getAllFavorites() {
        Long userId = ContextUtils.getCurrentId();
        // 先获取用户所有的收藏夹 和 每个收藏夹包含的数量
        List<FavoritesVo> favoritesVoList = favoritesMapper.getAllFavorites(userId);

        // 判断收藏夹是否为空
        for (FavoritesVo favoritesVo : favoritesVoList) {
            // 该收藏夹为空-就不再设置封面
            if (favoritesVo.getNumber() == 0) {
                continue;
            }
            // 设置每一个收藏夹的封面
            String coverImg = favoritesMapper.getCoverImg(favoritesVo.getFavoritesId());
            favoritesVo.setCoverImg(coverImg);
        }

        // 最后添加用户的默认收藏夹
        // 分开全部收藏和收藏夹
        /*List<FavoriteVo> favorites = favoriteMapper.getFavoriteVo(userId);
        if (favorites.size() == 0) {
            throw new BaseException("暂无收藏菜谱，快去首页逛逛吧");
        }
        FavoritesVo favoritesVo = new FavoritesVo();
        favoritesVo.setName("全部收藏");
        favoritesVo.setNumber(favorites.size());
        favoritesVo.setCoverImg(favorites.get(0).getCoverImg());

        favoritesVoList.add(favoritesVo);*/
        return favoritesVoList;
    }

    /**
     * 添加一个收藏夹
     * 前端只需要传收藏夹名字即可
     *
     * @param favorites
     */
    @Override
    public Long insert(Favorites favorites) {
        favorites.setUserId(ContextUtils.getCurrentId());
        favoritesMapper.insertOne(favorites);
        return favorites.getFavoritesId();
    }

    /**
     * 获取收藏夹的菜谱列表
     *
     * @param favoritesId
     * @return
     */
    @Override
    public List<RecipeBasicVo> getRecipeList(Long favoritesId) {
        Favorite favorite = new Favorite();
        favorite.setFavoritesId(favoritesId);
        return favoritesMapper.getRecipeList(favorite);
    }

    @Override
    public Favorites getFavoritesInfo(Long favoritesId) {
        return favoritesMapper.selectById(favoritesId);
    }

}
