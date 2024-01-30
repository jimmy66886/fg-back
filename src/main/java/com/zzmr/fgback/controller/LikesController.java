package com.zzmr.fgback.controller;


import com.zzmr.fgback.bean.Likes;
import com.zzmr.fgback.dto.AddLikeDto;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.LikesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/likes")
@Api(tags = "点赞相关接口")
public class LikesController {

    @Autowired
    private LikesService likesService;

    @ApiOperation("新增点赞")
    @PostMapping("/add")
    public Result addLikes(@RequestBody AddLikeDto addLikeDto) {
        likesService.addLikes(addLikeDto);
        return Result.success();
    }

    @ApiOperation("查询用户是否点赞过菜谱")
    @GetMapping("/getLiked")
    public Result getLiked(@RequestParam("recipeId") Long recipeId) {
        Boolean isLiked = likesService.getLiked(recipeId);
        return Result.success(isLiked);
    }
}

