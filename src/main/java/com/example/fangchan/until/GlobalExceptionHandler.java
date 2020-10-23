package com.example.fangchan.until;

import com.laike.kuaidi.ex.ServiceException;
import com.laike.kuaidi.ex.userex.PasswordNotMatchException;
import com.laike.kuaidi.ex.userex.UserNotFoundException;
import com.laike.kuaidi.ex.userex.UsernameDuplicateException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 *
 * @author: weiZiHao
 * @create: 2020-08-20 17:15
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ServiceException.class})
    public JsonResult<Void> handleException(Exception ex) {
        JsonResult<Void> json = new JsonResult<>(ex);
        if (ex instanceof UsernameDuplicateException) {
            json.setCode("4000");
            json.setState("4000");
        } else if (ex instanceof UserNotFoundException) {
            json.setCode("4001");
            json.setState("4001");
        } else if (ex instanceof PasswordNotMatchException) {
            //下单状态!
            json.setCode("4002");
            json.setState("4002");
        }
        return json;
    }
}
