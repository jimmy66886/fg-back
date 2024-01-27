package com.zzmr.fgback.service.impl;

import com.zzmr.fgback.bean.Likes;
import com.zzmr.fgback.constant.LikesConstant;
import com.zzmr.fgback.dto.AddLikeDto;
import com.zzmr.fgback.mapper.LikesMapper;
import com.zzmr.fgback.service.LikesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
@Service
public class LikesServiceImpl extends ServiceImpl<LikesMapper, Likes> implements LikesService {

    @Autowired
    private LikesMapper likesMapper;

    /**
     * 新增点赞
     *
     * @param addLikeDto
     */
    @Override
    public void addLikes(AddLikeDto addLikeDto) {
        // TODO 从ThreadLocal中取出用户id
        Likes likes = new Likes();
        // 先假设为1
        likes.setUserId(1L);
        if (addLikeDto.getCommentId() != null) {
            likes.setContentType(LikesConstant.CommentType);
            likes.setContentId(addLikeDto.getCommentId());
        }
        if (addLikeDto.getRecipeId() != null) {
            likes.setContentType(LikesConstant.RecipeType);
            likes.setContentId(addLikeDto.getRecipeId());
        }
        likesMapper.insert(likes);
    }
}
