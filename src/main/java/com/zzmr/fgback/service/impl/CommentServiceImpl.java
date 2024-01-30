package com.zzmr.fgback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzmr.fgback.bean.Comment;
import com.zzmr.fgback.dto.CommentDto;
import com.zzmr.fgback.mapper.CommentMapper;
import com.zzmr.fgback.result.PageResult;
import com.zzmr.fgback.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.vo.CommentVo;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 根据菜谱id分页查询菜谱的顶级评论
     *
     * @param commentDto 分页查询条件
     * @return
     */
    @Override
    public PageResult getByRecipeId(CommentDto commentDto) {
        // 开启分页
        PageHelper.startPage(commentDto.getPage(), commentDto.getPageSize());
        Page<CommentVo> page = commentMapper.getTop(commentDto);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 根据顶级评论id分页查询顶级评论下的二级评论
     *
     * @param commentDto 分页查询条件
     * @return 分页结果
     */
    @Override
    public PageResult getByTopComment(CommentDto commentDto) {
        PageHelper.startPage(commentDto.getPage(), commentDto.getPageSize());
        Page<CommentVo> page = commentMapper.getByTopComment(commentDto);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 新增评论
     *
     * @param comment
     */
    @Override
    public void add(Comment comment) {
        // TODO userId还是从ThreadLocal中取
        comment.setUserId(1L);
        commentMapper.insert(comment);
    }

    /**
     * 删除评论以及子评论
     *
     * @param commentId
     */
    @Override
    public void delete(Long commentId) {
        // 根据评论id删除根评论id，然后再删除所有rootId为该根评论id的评论
        commentMapper.delete(new LambdaQueryWrapper<Comment>()
                .eq(Comment::getCommentId, commentId)
                .or()
                .eq(Comment::getRootId, commentId)
        );
    }
}
