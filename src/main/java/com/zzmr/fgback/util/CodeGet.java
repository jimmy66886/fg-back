package com.zzmr.fgback.util;

/**
 * @author zzmr
 * @create 2024-01-13 22:24
 */

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGet {

    public static void main(String[] args) {

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("D:\\study\\GraduationDesign\\code\\fg-back\\src\\main\\java");

        gc.setServiceName("%sService");    // 去掉Service接口的首字母I
        gc.setAuthor("zzmr");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/db_food_guide?serverTimezone=GMT%2B8&useSSL=false");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("010203");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.zzmr");
        pc.setModuleName("fgback"); // 模块名
        pc.setController("controller.app");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();

        // 指定表名
        // strategy.setInclude("category");
        // strategy.setInclude("comment");
        // strategy.setInclude("favorite");
        // strategy.setInclude("likes");
        // strategy.setInclude("recipe");
        // strategy.setInclude("tag");
        // strategy.setInclude("user");
        // strategy.setInclude("favorites","recipe_step");
        // strategy.setInclude("vegetable_basket");
        strategy.setInclude("message");

        strategy.setNaming(NamingStrategy.underline_to_camel);// 数据库表映射到实体的命名策略

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);// 数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); // restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); // url中驼峰转连字符

        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();
    }
}
