package com.example.fangchan.until;

import lombok.Data;

/**
 * 用于封装响应客户端的JSON数据的属性的类
 *
 * @param <T>
 * @author weiZiHao
 */
@Data
public class JsonResult<T> {

    private String msg;
    private String code;//响应状态
    private String state;//响应状态
    private String message;//操作失败时的提示信息
    private Integer tocal;
    private T data;//操作成功时候响应客户端的数据

    public JsonResult() {
        super();
    }

    public JsonResult(Throwable e) {
        super();
        this.message = e.getMessage();
        this.msg = e.getMessage();
    }

    public JsonResult(String state, T data) {
        super();
        this.code = state;
        this.state = state;
        this.data = data;
    }

    public JsonResult(String state, T data, Integer tocal) {
        super();
        this.code = state;
        this.state = state;
        this.data = data;
        this.tocal = tocal;
    }

    public JsonResult(String state) {
        super();
        this.code = state;
        this.state = state;
    }

    public JsonResult(String state, String msg) {
        super();
        this.code = state;
        this.state = state;
        this.msg = msg;
        this.message = message;
    }

}
