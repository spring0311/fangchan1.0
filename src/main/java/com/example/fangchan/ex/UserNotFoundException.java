package com.example.fangchan.ex;

/**
 * 用户不存在
 * @author: weiZiHao
 * @create: 2020-08-20 17:11
 */
public class UserNotFoundException extends ServiceException {

    private static final long serialVersionUID = 7348637062861337837L;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
