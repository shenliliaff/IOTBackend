package com.up.iotbackend.entity;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import org.springframework.context.MessageSource;

import java.io.Serializable;
import java.util.Locale;

/**
 * @Author Bcheng
 * @Create 2021/6/24 下午 5:58
 * @Description <p>通用ResultData类</p>
 */
@Data
public class ResultData<T> implements Serializable {
    // 消息状态码
    private Integer code;
    // 消息
    private String msg;
    // 返回数据
    private T data;

    private ResultData(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 响应成功(带返回数据)
     * @param data 返回数据
     * @return Result
     */
    public static <T> ResultData success(T data) {
        ResultData resultData = ResultData.bind(ResultEnum.SUCCESS);
        resultData.setData(data);
        return resultData;
    }

    /**
     * 响应成功
     * @return Result
     */
    public static ResultData success() {
        return success(null);
    }

    public static ResultData error(ResultEnum messageNot) {
        return ResultData.bind(ResultEnum.FAILURE);
    }

    /**
     * 响应错误(不带状态码,带消息)
     * @return Result
     */
    public static ResultData error(String msg) {
        return error( ResultEnum.FAILURE.getCode(), msg);
    }

    public static ResultData error(Integer code,String message) {
        return new ResultData( code, message, null);
    }

    public static ResultData error() {
        return ResultData.bind(ResultEnum.FAILURE);
    }

    public static ResultData bind(ResultEnum resultEnum){
        String language = "en";

//        if (StpUtil.isLogin()){
//            language = StpUtil.getSession().getString("language");
//            if (language.contains("zh")){
//                language = "zh";
//            }
//        }
//
        MessageSource messageSource = SpringUtil.getBean(MessageSource.class);
        String message = messageSource.getMessage(resultEnum.getMessage(), null, new Locale(language));
        return new ResultData(resultEnum.getCode(),message,null);
    }
}
