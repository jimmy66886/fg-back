package com.zzmr.fgback.controller.app;

import com.zzmr.fgback.exception.BaseException;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.util.MinioUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zzmr
 * @create 2024-01-28 12:52
 */
@RestController
@Slf4j
@Api(tags = "文件上传")
@CrossOrigin
public class MinioController {

    @Autowired
    private MinioUtils minioUtils;

    @ApiOperation("上传一个")
    @PostMapping("/upload")
    public Result upload(@RequestParam(name = "file") MultipartFile file) {
        if (file == null) {
            throw new BaseException("文件为空！");
        }
        String imgUrl = minioUtils.upload(file);
        return Result.success(imgUrl);
    }

}
