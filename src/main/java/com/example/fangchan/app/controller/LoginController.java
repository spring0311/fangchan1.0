package com.example.fangchan.app.controller;

import com.example.fangchan.app.entity.TWechat;
import com.example.fangchan.until.BaseController;
import com.example.fangchan.until.JsonResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping("picture")
    public JsonResult<List<String>> getLoginPicture() {
        List<String> list = new ArrayList<>();
        list.add("http://42.192.6.15:8080/picture/one.jpg");
        list.add("http://42.192.6.15:8080/picture/two.jpg");
        list.add("http://42.192.6.15:8080/picture/three.jpg");
        list.add("http://42.192.6.15:8080/picture/four.jpg");
        return new JsonResult<>(OK, list);
    }
}
