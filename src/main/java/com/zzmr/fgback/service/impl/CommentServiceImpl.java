package com.zzmr.fgback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzmr.fgback.bean.Comment;
import com.zzmr.fgback.bean.User;
import com.zzmr.fgback.dto.CommentDTO;
import com.zzmr.fgback.mapper.CommentMapper;
import com.zzmr.fgback.mapper.UserMapper;
import com.zzmr.fgback.result.PageResult;
import com.zzmr.fgback.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult getByRecipeId(CommentDTO commentDTO) {
        // 开启分页
        PageHelper.startPage(commentDTO.getPage(), commentDTO.getPageSize());

        // 查询顶级评论条件组合
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Comment::getRecipeId, commentDTO.getRecipeId())
                .isNull(Comment::getRootId).isNull(Comment::getToId);
        Page<Comment> comments = (Page<Comment>) commentMapper.selectList(lambdaQueryWrapper);

        List<CommentVO> commentVOS = new ArrayList<>();
        for (Comment comment : comments) {
            User sender = userMapper.selectById(comment.getUserId());
            CommentVO commentVO = CommentVO.builder().senderId(sender.getUserId()).senderName(sender.getNickName())
                    .senderAvatarUrl(sender.getAvatarUrl()).content(comment.getContent()).sendDateTime(comment.getCreateTime())
                    .CommentId(comment.getCommentId())
                    .build();
            commentVOS.add(commentVO);
        }
        return new PageResult(comments.getTotal(), commentVOS);
    }

    @Override
    public PageResult getByTopComment(CommentDTO commentDTO) {
        /**
         * 肯定是有菜谱id recipeId
         * 也会有rootId，该值应该和顶级评论id相等
         * 如果是二级评论的评论，toId也不为空
         */
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Comment::getRecipeId, commentDTO.getRecipeId())
                .eq(Comment::getRootId, commentDTO.getRootId());
        // 根据recipeId和rootId就可以查出该顶级评论下的二级评论了（包括回复顶级评论和不回复顶级评论的）

        // 返回结果VO
        List<CommentVO> commentVOS = new ArrayList<>();

        // 加入分页
        PageHelper.startPage(commentDTO.getPage(), commentDTO.getPageSize());
        Page<Comment> commentPage = (Page<Comment>) commentMapper.selectList(lambdaQueryWrapper);
        List<Comment> comments = commentPage.getResult();
        for (Comment comment : comments) {
            if (comment.getToId() != null) {
                // 说明这个评论是回复其他二级评论的----如果是回复顶级评论，这里是空的
                Long senderId = comment.getUserId();
                Long receiveId = comment.getToId();

                /**
                 * 根据发送者id和接收者id查询这两个用户的基本信息
                 * 后续还要添加头像链接
                 */
                // 第一个是 发送者，第二个是接收者
                User sender = userMapper.selectById(senderId);
                User receiver = userMapper.selectById(receiveId);
                CommentVO commentVO =
                        CommentVO.builder().senderName(sender.getNickName()).senderId(senderId).senderAvatarUrl(sender.getAvatarUrl())
                                .receiveName(receiver.getNickName()).receiverId(receiveId).receiverAvatarUrl(receiver.getAvatarUrl())
                                .content(comment.getContent()).sendDateTime(comment.getCreateTime()).CommentId(comment.getCommentId())
                                .build();
                commentVOS.add(commentVO);
            } else {
                // 顶级评论
                User sender = userMapper.selectById(comment.getUserId());
                CommentVO commentVO =
                        CommentVO.builder().senderName(sender.getNickName()).senderId(comment.getCommentId()).senderAvatarUrl(sender.getAvatarUrl())
                                .content(comment.getContent()).sendDateTime(comment.getCreateTime()).CommentId(comment.getCommentId())
                                .build();
                commentVOS.add(commentVO);
            }
        }

        return new PageResult(commentPage.getTotal(), commentVOS);
    }
}
