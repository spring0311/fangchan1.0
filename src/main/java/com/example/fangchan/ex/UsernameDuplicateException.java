package com.example.fangchan.ex;

/**
 * 用户名已存在
 * @author: weiZiHao
 * @create: 2020-08-20 17:19
 */
public class UsernameDuplicateException extends ServiceException {
    private static final long serialVersionUID = 4870560576222690461L;

    public UsernameDuplicateException() {
    }

    public UsernameDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UsernameDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicateException(String message) {
        super(message);
    }

    public UsernameDuplicateException(Throwable cause) {
        super(cause);
    }
}
