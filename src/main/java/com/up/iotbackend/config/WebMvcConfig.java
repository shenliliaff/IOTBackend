package com.up.iotbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
//        registry.addResourceHandler(ProfilePhotoMapperPath+"**").addResourceLocations("file:"+ProfilePhotoPath);
        //因为swagger与静态文件访问配置冲突，所以整合swagger需要
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //关闭原生swagger-ui, 自动redirect去根目录
        //registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/");
        //新的api document ui
        //registry.addResourceHandler("/v3/api-docs/**").addResourceLocations("classpath:/swagger/");
        super.addResourceHandlers(registry);
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui/").setViewName("forward:/swagger-ui/index.html");
    }


//    @Bean
//    public StringHttpMessageConverter stringHttpMessageConverter() {
//        return new StringHttpMessageConverter();
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        //字符串转换器
//        List<MediaType> listString = new ArrayList<MediaType>();
//        //字符串的消息类型为text/plain
//        listString.add(MediaType.TEXT_PLAIN);
//        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
//        stringHttpMessageConverter.setSupportedMediaTypes(listString);
//
//        //json转换器
//        List<MediaType> list = new ArrayList<MediaType>();
//        list.add(MediaType.APPLICATION_JSON_UTF8);
//        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
//
//        converters.add(mappingJackson2HttpMessageConverter);
//        converters.add(stringHttpMessageConverter);
//        super.configureMessageConverters(converters);
//    }



//    放过权限控制
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor()
//                .addPathPatterns("/**")
//                .excludePathPatterns("/swagger**/**")
//                .excludePathPatterns("/webjars/**")
//                .excludePathPatterns("/v3/**")
//                .excludePathPatterns("/doc.html");
//        super.addInterceptors(registry);
//    }
}
