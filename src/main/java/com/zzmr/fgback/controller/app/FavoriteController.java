package com.zzmr.fgback.controller.app;


import com.zzmr.fgback.bean.Favorite;
import com.zzmr.fgback.dto.PageFavoriteDto;
import com.zzmr.fgback.result.PageResult;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.FavoriteService;
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
@CrossOrigin
@Api(tags = "收藏相关接口")
@Slf4j
@RequestMapping("/app/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    /**
     * 用户点击收藏，会直接收藏进全部收藏夹
     * 然后用户可以选择自定义收藏夹，选择后该菜谱仍然存在于全部收藏夹
     *
     * @param recipeId
     * @return
     */
    @PostMapping("/insert")
    @ApiOperation("添加一个菜谱收藏")
    public Result insert(Long recipeId) {
        favoriteService.insert(recipeId);
        return Result.success();
    }

    @PostMapping("/delete")
    @ApiOperation("删除一个菜谱收藏")
    public Result delete(Long recipeId) {
        favoriteService.delete(recipeId);
        return Result.success();
    }

    @GetMapping("/getFavorited")
    @ApiOperation("查询用户是否收藏过菜谱")
    public Result getFavorited(@RequestParam Long recipeId) {
        Boolean isFavorited = favoriteService.getFavorited(recipeId);
        return Result.success(isFavorited);
    }

    /**
     * 分页查询用户的全部收藏
     *
     * @param pageFavoriteDto
     * @return
     */
    @PostMapping("/getAll")
    @ApiOperation("分页查询用户的全部收藏")
    public PageResult getAll(@RequestBody PageFavoriteDto pageFavoriteDto) {
        PageResult pageResult = favoriteService.getAll(pageFavoriteDto);
        return pageResult;
    }

    /**
     * 用户收藏该菜谱至全部收藏后，可选择将收藏存放至自定义收藏夹
     * 这时只需要根据 菜谱id和用户id 以及 收藏夹id favoritesId
     * 将这条记录的favoritesId 修改为指定的收藏夹id即可
     * 限定条件：一个菜谱只能收藏进一个自定义收藏夹
     *
     * @return
     */
    @PostMapping("/addTo")
    @ApiOperation("添加到指定自定义收藏夹")
    public Result addTo(@RequestBody Favorite favorite) {
        favoriteService.addTo(favorite);
        return Result.success();
    }


}

