package com.zzmr.fgback.service;

import com.zzmr.fgback.bean.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzmr.fgback.dto.CommentDTO;
import com.zzmr.fgback.result.PageResult;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
public interface CommentService extends IService<Comment> {

    /**
     * 根据菜谱id分页查询菜谱的顶级评论
     * @param commentDTO 分页查询条件
     * @return 分页结果
     */
    PageResult getByRecipeId(CommentDTO commentDTO);

    /**
     * 根据顶级评论id分页查询顶级评论下的二级评论
     * @param commentDTO 分页查询条件
     * @return 分页结果
     */
    PageResult getByTopComment(CommentDTO commentDTO);
}
