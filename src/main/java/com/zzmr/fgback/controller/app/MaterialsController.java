package com.zzmr.fgback.controller.app;


import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.MaterialsService;
import com.zzmr.fgback.vo.MaterialsVo;
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
 * @since 2024-01-23
 */
@RestController
@CrossOrigin
@Slf4j
@Api(tags = "食材相关接口")
@RequestMapping("/app/materials")
public class MaterialsController {

    @Autowired
    private MaterialsService materialsService;

    @ApiOperation("获取全部食材")
    @GetMapping("/getAll")
    public Result getAll() {
        List<MaterialsVo> materialsVos = materialsService.getAll();
        return Result.success(materialsVos);
    }

}

