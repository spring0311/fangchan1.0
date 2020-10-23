package com.example.fangchan.ex;

/**
 * 异常类基类
 * @author: weiZiHao
 * @create: 2020-08-20 16:57
 */
public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = -7584694378098185068L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
