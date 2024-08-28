package com.zzmr.fgback.util;

import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zzmr
 * @create 2024-05-31 10:39
 * 敏感词工具类
 */
@Component
public class SensitiveUtil {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 获取敏感词库
     * 返回结果是List集合
     * 来源是redis缓存中的敏感词
     * @return
     */
    public List<String> getSensitive(){
        List<String> sensitive = redisUtils.getList("sensitive", String.class);
        return sensitive;
    }

    /**
     * 设置敏感词库
     * 管理端上传excel
     */
    public void setSensitive(InputStream inputStream) throws IOException {
        List<String> sensitive = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(inputStream);

        // 第一个sheet
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            // 第一行
            Row row = sheet.getRow(i);

            // 转换为字符类型
            for (Cell cell : row) {
                cell.setCellType(CellType.STRING);
            }
            sensitive.add(row.getCell(0).getStringCellValue());
        }
        workbook.close();
        redisUtils.setList("sensitive",sensitive);
    }

}
