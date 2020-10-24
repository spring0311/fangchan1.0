package com.example.fangchan.app.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.fangchan.app.entity.TScore;
import com.example.fangchan.app.entity.TScoreRecord;
import com.example.fangchan.until.BaseController;
import com.example.fangchan.until.JsonResult;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 积分
 * 前端控制器
 * </p>
 *
 * @author weizihao
 * @since 2020-10-24
 */
@RestController
@RequestMapping("score")
@Transactional
@CrossOrigin
public class TScoreController extends BaseController {


    /**
     * 我的积分
     *
     * @param tScore scoreId积分Id
     * @return
     */
    @RequestMapping("mine")
    public JsonResult<TScore> getScore(TScore tScore) {
        return new JsonResult<>(OK, scoreService.getById(tScore.getScoreId()));
    }

    /**
     * 积分记录
     *
     * @param scoreRecord scoreId积分表ID upDown加减
     * @return
     */
    @RequestMapping("list")
    public JsonResult<List<TScoreRecord>> getList(TScoreRecord scoreRecord) {
        QueryWrapper<TScoreRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(scoreRecord);
        return new JsonResult<>(OK, scoreRecordService.list(queryWrapper));
    }

}

