package com.up.iotbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.oas.annotations.EnableOpenApi;

@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket defaultApi3() {
        return new Docket(DocumentationType.OAS_30).pathMapping("/")
                .enable(true)
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                // 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现
                .select()
                //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)) //扫描我的项目中所有带有@Api注解的项目
                .apis(RequestHandlerSelectors.basePackage("com.up.iotbackend.controller")) //只扫controller下面的@Api
                // 对所有路径进行监控
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title("API接口文档")
                // 描述
                .description("描述:up iot interfaces")
                // 版本
                .version("版本号:" + "1.0")
                .contact(new Contact("up", "https://upcloudtech.cn", "uptech_iot@163.com"))
                .licenseUrl("https://upcloudtech.cn")
                .termsOfServiceUrl("https://upcloudtech.cn")
                .build();
    }
}

