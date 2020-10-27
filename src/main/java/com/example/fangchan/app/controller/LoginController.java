package com.example.fangchan.app.controller;

import com.example.fangchan.app.entity.TWechat;
import com.example.fangchan.until.BaseController;
import com.example.fangchan.until.JsonResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin2.message.JavaScriptBaseMessage;

/**
 * @author: weiZiHao
 * @create: 2020-10-23 09:35
 */
@RestController
@RequestMapping("login")
@Transactional
@CrossOrigin
public class LoginController extends BaseController {

    @RequestMapping("test")
    public JsonResult<TWechat> test(TWechat tWechat) {
        return new JsonResult<>(OK, wechatService.getById(1));
    }
}
