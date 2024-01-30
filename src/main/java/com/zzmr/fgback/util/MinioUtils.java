package com.zzmr.fgback.util;

import com.zzmr.fgback.properties.MinioProperties;
import com.zzmr.fgback.exception.BaseException;
import com.zzmr.fgback.result.Result;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author zzmr
 * @create 2024-01-28 13:58
 */
@Component
public class MinioUtils {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Autowired
    private MinioProperties minioProperties;

    public String upload(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        // 获取文件后缀
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        // 使用UUID来代替原文件名，避免重复
        UUID uuidName = UUID.randomUUID();
        String imgName = uuidName + suffix;
        try {
            // 文件上传
            InputStream in = file.getInputStream();
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(imgName)
                    .stream(in, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());
            in.close();
            System.out.println("上传成功: " + imgName);

            // http://47.109.139.173:9000/food.guide/%E5%B0%8F%E5%9B%BE.jpg
            String imgUrl = minioProperties.getEndpoint() + "/" + bucketName + "/" + imgName;
            return imgUrl;
        } catch (Exception e) {
            throw new BaseException("上传失败");
        }
    }

}
