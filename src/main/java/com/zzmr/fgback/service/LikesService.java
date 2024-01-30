package com.zzmr.fgback.service;

import com.zzmr.fgback.bean.Likes;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzmr.fgback.dto.AddLikeDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
public interface LikesService extends IService<Likes> {

    /**
     * 新增点赞
     * @param addLikeDto
     */
    void addLikes(AddLikeDto addLikeDto);

    /**
     * 查询用户是否点赞过菜谱
     * @param recipeId
     * @return
     */
    Boolean getLiked(Long recipeId);
}
