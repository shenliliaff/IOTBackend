package com.up.iotbackend.exception;

/**
 * @Author Bcheng
 * @Create 2021/6/24 下午 6:11
 * @Description <p>默认描述</p>
 */
public class GlobalException extends RuntimeException {
    public GlobalException() {
        super();
    }

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlobalException(Throwable cause) {
        super(cause);
    }

    protected GlobalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
