package com.zzmr.fgback.config;

/**
 * @author zzmr
 * @create 2023-10-14 7:53
 */


import com.zzmr.fgback.interceptor.JwtTokenAdminInterceptor;
import com.zzmr.fgback.interceptor.JwtTokenUserInterceptor;
import com.zzmr.fgback.json.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 * 配置类，注册web层相关组件
 */
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    @Autowired
    private JwtTokenUserInterceptor jwtTokenUserInterceptor;

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/user/login")
                .excludePathPatterns("/admin/user/uploadSensitive");
        registry.addInterceptor(jwtTokenUserInterceptor)
                .addPathPatterns("/app/**")
                .excludePathPatterns("/app/user/login")
                .excludePathPatterns("/app/user/register")
                // 由于之前的getList涉及到用户搜索记录的插入，所以这里新加一个getNormalList，用于主页的菜谱展示
                .excludePathPatterns("/app/recipe/getNormalList")
                .excludePathPatterns("/app/recipe/test")
                .excludePathPatterns("/app/user/getCode")
                .excludePathPatterns("/app/recipe/getById")
                .excludePathPatterns("/app/recipe/getByMaterials")
                .excludePathPatterns("/app/category/getAll")
                .excludePathPatterns("/app/oneWord/get")
                .excludePathPatterns("/upload");
    }

    /**
     * 扩展SpringMVC框架的消息转换器
     * 对后端返回给前端的数据进行 统一 的处理
     *
     * @param converters the list of configured converters to extend
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("扩展消息转换器-用于日期格式化");
        // 创建一个消息转换对象
        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter();
        // 需要为消息转换器设置一个对象转换器,可以将Java对象序列化为json数据
        converter.setObjectMapper(new JacksonObjectMapper());
        // 将自己的消息转换器加入到容器中,第一个参数是代表索引,也即是这个转换器的优先级,越小越高
        converters.add(0, converter);

    }

    /**
     * 通过knife4j生成接口文档
     *
     * @return
     */
    @Bean
    public Docket docket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("接口文档")
                .version("2.0")
                .description("美食教程小程序用户端接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户端接口")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zzmr.fgback.controller.app"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    @Bean
    public Docket docket1() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("接口文档")
                .version("2.0")
                .description("美食教程小程序管理端接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端接口")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zzmr.fgback.controller.admin"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * 设置静态资源映射
     *
     * @param registry
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}