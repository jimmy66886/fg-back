package com.zzmr.fgback.controller.app;


import com.zzmr.fgback.bean.Comment;
import com.zzmr.fgback.dto.CommentDto;
import com.zzmr.fgback.dto.DeleteCommentDto;
import com.zzmr.fgback.result.PageResult;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
@RestController
@RequestMapping("/app/comment")
@Api(tags = "评论相关接口")
@Slf4j
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 应该是在查询菜谱中的评论时,就将那条评论是否点过赞给封装到单条评论中
     * 1. 首先是顶级评论,在返回顶级评论时,返回结果中应包含该条评论是否点过赞
     *
     * @param commentDto
     * @return
     */
    @ApiOperation("根据菜谱id分页查询菜谱的顶级评论")
    @PostMapping("/getByRecipeId")
    public PageResult getByRecipeId(@RequestBody CommentDto commentDto) {
        PageResult pageResult = commentService.getByRecipeId(commentDto);
        return pageResult;
    }

    @ApiOperation("根据顶级评论id分页查询顶级评论下的二级评论")
    @PostMapping("/getByTopComment")
    public PageResult getByTopComment(@RequestBody CommentDto commentDto) {
        PageResult pageResult = commentService.getByTopComment(commentDto);
        return pageResult;
    }

    @ApiOperation("新增评论")
    @PostMapping("/add")
    public Result add(@RequestBody Comment comment) {
        commentService.add(comment);
        return Result.success();
    }

    @ApiOperation("删除评论")
    @PostMapping("/delete")
    public Result delete(@RequestBody DeleteCommentDto dto) {
        commentService.delete(dto.getCommentId());
        return Result.success();
    }

}

