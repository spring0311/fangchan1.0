package com.example.fangchan.app.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.fangchan.app.entity.TScore;
import com.example.fangchan.app.entity.TWechat;
import com.example.fangchan.config.WeChatConfig;
import com.example.fangchan.until.BaseController;
import com.example.fangchan.until.JsonResult;
import com.example.fangchan.until.WeChatIV;
import com.example.fangchan.until.WeChatUtil;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 * 微信用户
 * 前端控制器
 * </p>
 *
 * @author weizihao
 * @since 2020-10-24
 */
@RestController
@RequestMapping("wechat")
@Transactional
@CrossOrigin
public class TWechatController extends BaseController {

    //https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
    //https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN

    /**
     * 微信登录
     *
     * @param weChatIV code
     * @return
     */
    @RequestMapping("login")
    public JsonResult<TWechat> login(WeChatIV weChatIV) {
        /**
         * 得到openid access_token
         */
        String url = getUrl(weChatIV.getCode());
        String getBack = WeChatUtil.httpRequest(url, "get", null);
        JSONObject jsonObject = JSONObject.parseObject(getBack);
        String openid = jsonObject.get("openid").toString();
        String accessToken = jsonObject.get("access_token").toString();
        /**
         * 得到用户信息
         */
        String urlForUser = getUrlForUser(openid, accessToken);
        String userBack = WeChatUtil.httpRequest(urlForUser, "get", null);
        JSONObject user = JSONObject.parseObject(userBack);
        /**
         * 登陆逻辑
         */
        TWechat tWechat = getUser(user);
        return new JsonResult<>(OK, tWechat);
    }

    /**
     * 返回登陆用户
     *
     * @param user
     * @return
     */
    private TWechat getUser(JSONObject user) {
        QueryWrapper<TWechat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("OPEN_ID", user.get("openid").toString());
        TWechat dao = wechatService.getOne(queryWrapper);
        //新建用户
        if (dao == null) {
            /**
             * 新建用户与积分表
             */
            TWechat tWechat = new TWechat();
            Long scoreId = getTimeId();
            tWechat.setOpenId(user.get("openid").toString());
            tWechat.setNickName(user.get("nickname").toString());
            tWechat.setAva(user.get("headimgurl").toString());
            tWechat.setCreateTime(new Date());
            tWechat.setModifyTime(new Date());
            tWechat.setScoreId(scoreId);
            wechatService.saveOrUpdate(tWechat);
            TScore tScore = new TScore();
            tScore.setScoreId(scoreId);
            tScore.setWechatId(tWechat.getWechatId());
            tScore.setCreateTime(new Date());
            tScore.setModifyTime(new Date());
            scoreService.saveOrUpdate(tScore);
            //老用户
            return tWechat;
        } else {
            dao.setNickName(user.get("nickname").toString());
            dao.setAva(user.get("headimgurl").toString());
            dao.setModifyTime(new Date());
            wechatService.saveOrUpdate(dao);
            return dao;
        }
    }


    /**
     * 得到获取 openid access_token 的接口地址
     * <p>
     * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
     *
     * @return
     */
    private String getUrl(String code) {
        String start = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=";
        String next = "&secret=";
        String again = "&code=";
        String end = "&grant_type=authorization_code";
        String url = start + userAppId + next + userAppSecret + again + code + end;
        return url;
    }

    /**
     * 得到用户信息的 接口地址
     * //https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
     *
     * @param
     * @return
     */
    private String getUrlForUser(String openid, String token) {
        String start = "https://api.weixin.qq.com/sns/userinfo?access_token=";
        String next = "&openid=";
        String end = "&lang=zh_CN";
        String url = start + token + next + openid + end;
        return url;
    }
}

