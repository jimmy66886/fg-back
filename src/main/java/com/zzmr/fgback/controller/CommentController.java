package com.zzmr.fgback.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzmr.fgback.bean.Comment;
import com.zzmr.fgback.bean.User;
import com.zzmr.fgback.dto.CommentDTO;
import com.zzmr.fgback.result.PageResult;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.CommentService;
import com.zzmr.fgback.service.UserService;
import com.zzmr.fgback.vo.CommentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "评论相关接口")
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @ApiOperation("根据菜谱id查询菜谱所有的顶级评论")
    @PostMapping("/getByRecipeId")
    public Result getByRecipeId(@RequestBody CommentDTO commentDTO) {
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Comment::getRecipeId, commentDTO.getRecipeId())
                .isNull(Comment::getRootId).isNull(Comment::getToId);
        List<Comment> comments = commentService.list(lambdaQueryWrapper);
        return Result.success(comments);
    }

    @ApiOperation("根据顶级评论id查询顶级评论下所有的二级评论")
    @PostMapping("/getByTopComment")
    public PageResult getByTopComment(@RequestBody CommentDTO commentDTO) {
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
        Page<Comment> commentPage = (Page<Comment>) commentService.list(lambdaQueryWrapper);
        List<Comment> comments = commentPage.getResult();
        for (Comment comment : comments) {
            if (comment.getToId() != null) {
                // 说明这个评论是回复其他二级评论的----如果是回复顶级评论，这里是空的
                Long senderId = comment.getUserId();
                Long receiveId = comment.getToId();
                System.out.println("发起者:" + senderId + "  接收者:" + receiveId + "内容:" + comment.getContent());
                CommentVO commentVO = CommentVO.builder().senderName(senderId.toString() + "发送者姓名：").senderId(senderId)
                        .receiveName(receiveId.toString() + "接收者姓名").receiveId(receiveId).content(comment.getContent())
                        .build();
                commentVOS.add(commentVO);
            } else {
                // 顶级评论
                CommentVO commentVO = CommentVO.builder().senderName(comment.getUserId()
                                .toString() + "发送者姓名").senderId(comment.getId())
                        .content(comment.getContent())
                        .build();
                commentVOS.add(commentVO);
            }
        }

        return new PageResult(commentPage.getTotal(),commentVOS);
    }

}

