package com.example.fangchan.app.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.fangchan.app.entity.TComm;
import com.example.fangchan.app.entity.TScore;
import com.example.fangchan.app.entity.TScoreRecord;
import com.example.fangchan.app.entity.TWechat;
import com.example.fangchan.ex.UsernameDuplicateException;
import com.example.fangchan.until.BaseController;
import com.example.fangchan.until.JsonResult;
import net.sf.jsqlparser.expression.operators.relational.JsonOperator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品
 * 前端控制器
 * </p>
 *
 * @author weizihao
 * @since 2020-10-24
 */
@RestController
@RequestMapping("comm")
@Transactional
@CrossOrigin
public class TCommController extends BaseController {

    /**
     * 列表 单个
     *
     * @param tComm commodityId 商品Id
     * @return
     */
    @RequestMapping("list")
    public JsonResult<List<TComm>> commList(TComm tComm) {
        QueryWrapper<TComm> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(tComm);
        List<TComm> list = commService.list(queryWrapper);
        return new JsonResult<>(OK, list);
    }


    /**
     * 商品兑换
     *
     * @param tComm wechatId 用户Id   commodityId 商品Id
     * @return
     */
    @RequestMapping("buy")
    public JsonResult<Void> buyComm(TComm tComm) {
        QueryWrapper<TScore> scoreQueryWrapper = new QueryWrapper<>();
        scoreQueryWrapper.eq("WECHAT_ID", tComm.getWechatId());
        TScore tScore = scoreService.getOne(scoreQueryWrapper);
        TWechat tWechat = wechatService.getById(tComm.getWechatId());
        TComm dao = commService.getById(tComm.getCommodityId());
        if (tScore.getSum() < dao.getMoney()) {
            throw new UsernameDuplicateException("积分不足");
        }
        /**
         * 兑换 修改总积分
         */
        tScore.setSum(tScore.getSum() - dao.getMoney());
        tScore.setModifyTime(new Date());
        scoreService.saveOrUpdate(tScore);
        /**
         * 记录
         */
        TScoreRecord tScoreRecord = new TScoreRecord();
        tScoreRecord.setScoreId(tScore.getScoreId());
        tScoreRecord.setNickName(tWechat.getNickName());
        tScoreRecord.setUpDown(1);
        tScoreRecord.setName(dao.getName());
        tScoreRecord.setPicture(dao.getPicture());
        tScoreRecord.setNum(dao.getMoney());
        tScoreRecord.setCreateTime(new Date());
        tScoreRecord.setModifyTime(new Date());
        scoreRecordService.saveOrUpdate(tScoreRecord);
        return new JsonResult<>(OK);
    }


}

