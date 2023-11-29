package com.up.iotbackend.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.ResultEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = NotLoginException.class)
    public ResultData getNotLoginException(NotLoginException e) {
        log.error(e);
        if (NotLoginException.NOT_TOKEN.equals(e.getType())){
            return ResultData.bind(ResultEnum.LOGIN_NONE);
        } if (NotLoginException.INVALID_TOKEN.equals(e.getType()) ||
                (NotLoginException.TOKEN_TIMEOUT.equals(e.getType()))){
            return ResultData.bind(ResultEnum.LOGIN_INVALID);
        } if (NotLoginException.BE_REPLACED.equals(e.getType()) ||
                (NotLoginException.KICK_OUT.equals(e.getType()))){
            return ResultData.bind(ResultEnum.LOGIN_OUT);
        }
        return ResultData.error(e.getMessage());
    }

    @ExceptionHandler(value = GlobalException.class)
    public ResultData getGlobalException(GlobalException e) {
        e.printStackTrace();
        return ResultData.error(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResultData getException(Exception e) {
        e.printStackTrace();
        return ResultData.error();
    }
}