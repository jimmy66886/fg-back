package com.zzmr.fgback.controller.app;


import com.zzmr.fgback.dto.AddFollowers;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.FollowersService;
import com.zzmr.fgback.vo.FollowersVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzmr
 * @since 2024-02-06
 */
@RestController
@CrossOrigin
@Api(tags = "关注相关接口")
@Slf4j
@RequestMapping("/app/followers")
public class FollowersController {

    @Autowired
    private FollowersService followersService;

    @ApiOperation("新增关注")
    @PostMapping("/insert")
    public Result insert(@RequestBody AddFollowers addFollowers) {
        followersService.insert(addFollowers);
        return Result.success();
    }

    @ApiOperation("取消关注")
    @GetMapping("/delete")
    public Result delete(@RequestParam Long followingId) {
        followersService.delete(followingId);
        return Result.success();
    }

    @ApiOperation("查看关注列表")
    @GetMapping("/getList")
    public Result getList() {
        List<FollowersVo> followersVoList = followersService.getList();
        return Result.success(followersVoList);
    }

    @ApiOperation("查看粉丝")
    @GetMapping("/getFans")
    public Result getFans() {
        List<FollowersVo> fans = followersService.getFans();
        return Result.success(fans);
    }



}

