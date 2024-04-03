package com.zzmr.fgback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzmr.fgback.bean.Likes;
import com.zzmr.fgback.constant.LikesConstant;
import com.zzmr.fgback.dto.AddLikeDto;
import com.zzmr.fgback.mapper.LikesMapper;
import com.zzmr.fgback.service.LikesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.util.ContextUtils;
import com.zzmr.fgback.vo.LikeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
        Likes likes = new Likes();
        // 先假设为1
        likes.setUserId(ContextUtils.getCurrentId());
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

    /**
     * 查询用户是否点赞过菜谱
     *
     * @param recipeId
     * @return
     */
    @Override
    public Boolean getLiked(Long recipeId) {
        Integer value = likesMapper.selectCount(new LambdaQueryWrapper<Likes>()
                .eq(Likes::getContentType, LikesConstant.RecipeType)
                .eq(Likes::getContentId, recipeId)
                .eq(Likes::getUserId, ContextUtils.getCurrentId())
        );
        if (value > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * 删除点赞记录
     * 这个方法可以删除评论点赞和菜谱点赞，所以要根据前端传来的id来判断是什么类型的点赞
     *
     * @param addLikeDto
     */
    @Override
    public void delete(AddLikeDto addLikeDto) {
        String contentType = "";
        Long contentId = null;
        if (addLikeDto.getRecipeId() != null) {
            // 取消菜谱点赞
            contentType = "recipe";
            contentId = addLikeDto.getRecipeId();
        }
        if (addLikeDto.getCommentId() != null) {
            // 取消评论点赞
            contentType = "comment";
            contentId = addLikeDto.getCommentId();
        }
        likesMapper.delete(new LambdaQueryWrapper<Likes>()
                .eq(Likes::getContentType, contentType)
                .eq(Likes::getUserId, ContextUtils.getCurrentId())
                .eq(Likes::getContentId, contentId)
        );
    }

    /**
     * 查询收到的点赞
     *
     * @return
     */
    @Override
    public List<LikeVo> getLikes() {

        Long userId = ContextUtils.getCurrentId();
        List<LikeVo> recipeLikes = likesMapper.getRecipeLikes(userId);
        recipeLikes.forEach(item -> item.setLikeType("recipe"));

        List<LikeVo> commentLikes = likesMapper.getCommentLikes(userId);
        commentLikes.forEach(item -> item.setLikeType("comment"));
        recipeLikes.addAll(commentLikes);

        // 根据点赞时间降序排序
        recipeLikes.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));

        return recipeLikes;
    }
}
