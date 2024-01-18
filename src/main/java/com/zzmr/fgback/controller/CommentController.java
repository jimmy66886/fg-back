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
import org.springframework.transaction.annotation.Transactional;
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
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation("根据菜谱id分页查询菜谱的顶级评论")
    @PostMapping("/getByRecipeId")
    public PageResult getByRecipeId(@RequestBody CommentDTO commentDTO) {
        PageResult pageResult = commentService.getByRecipeId(commentDTO);
        return pageResult;
    }

    @ApiOperation("根据顶级评论id分页查询顶级评论下的二级评论")
    @PostMapping("/getByTopComment")
    public PageResult getByTopComment(@RequestBody CommentDTO commentDTO) {
        PageResult pageResult = commentService.getByTopComment(commentDTO);
        return pageResult;
    }

}

