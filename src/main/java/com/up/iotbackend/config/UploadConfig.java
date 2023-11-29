package com.up.iotbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class UploadConfig extends WebMvcConfigurationSupport {
    //@Value可以将配置文件的内容自动注入到属性内
    @Value("${savePath.profilePhotoPath}")
    private String ProfilePhotoPath;   //图标物理存储路径
    @Value("${savePath.profilePhotoMapper}")
    private String ProfilePhotoMapperPath;   //图标映射路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(ProfilePhotoMapperPath+"**").addResourceLocations("file:"+ProfilePhotoPath);
    }
}
