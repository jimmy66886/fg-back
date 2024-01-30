package com.zzmr.fgback.service;

import com.zzmr.fgback.bean.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzmr.fgback.dto.CommentDto;
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
     *
     * @param commentDto 分页查询条件
     * @return 分页结果
     */
    PageResult getByRecipeId(CommentDto commentDto);

    /**
     * 根据顶级评论id分页查询顶级评论下的二级评论
     *
     * @param commentDto 分页查询条件
     * @return 分页结果
     */
    PageResult getByTopComment(CommentDto commentDto);

    /**
     * 新增评论
     *
     * @param comment
     */
    void add(Comment comment);

    /**
     * 删除评论以及子评论
     *
     * @param commentId
     */
    void delete(Long commentId);
}
