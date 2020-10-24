package com.example.fangchan.app.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fangchan.app.entity.*;
import com.example.fangchan.until.BaseController;
import com.example.fangchan.until.JsonResult;
import com.example.fangchan.until.QueryRequest;
import com.sun.org.apache.bcel.internal.generic.LineNumberGen;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import javax.xml.bind.annotation.W3CDomHandler;
import javax.xml.transform.OutputKeys;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 订单
 * 前端控制器
 * </p>
 *
 * @author weizihao
 * @since 2020-10-24
 */
@RestController
@RequestMapping("order")
@Transactional
@CrossOrigin
public class TOrderController extends BaseController {


    /**
     * 单独的订单
     *
     * @param tOrder wechatId 用户ID 或者订单ID
     * @return
     */
    @RequestMapping("getOne")
    public JsonResult<TOrder> getMine(TOrder tOrder) {
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(tOrder);
        TOrder mine = orderService.getOne(queryWrapper);
        if (mine != null) {
            mine.setOWList(getTOrderWechat(mine));
            mine.setIsHelp(isHelp(mine.getOrderId(), tOrder.getWechatIdForSelect()));
        }
        return new JsonResult<>(OK, mine);
    }


    /**
     * 一次给十个
     * 首页订单列表
     *
     * @param tOrder       wechatIdForSelect 用户ID  pageNum页码数默认1
     * @param queryRequest
     * @return
     */
    @RequestMapping("index")
    public JsonResult<List<TOrder>> orderList(TOrder tOrder, QueryRequest queryRequest) {
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("PERCENTAGE", 1.00);
        queryWrapper.orderByDesc("PERCENTAGE");
        Page<TOrder> page = new Page<>(queryRequest.getPageNum(), queryRequest.getPageSize());
        page = orderService.page(page, queryWrapper);
        List<TOrder> tOrders = page.getRecords();
        //处理查询结果
        if (tOrders.size() > 0)
            tOrders.forEach(dao -> {
                //前十个头像
                dao.setOWList(getTOrderWechat(dao));
                //判断是否砍过
                dao.setIsHelp(isHelp(dao.getOrderId(), tOrder.getWechatIdForSelect()));
            });
        return new JsonResult<>(OK, tOrders);
    }


    /**
     * 新房源列表
     *
     * @param queryRequest pageNum页码数默认1 wechatIdForSelect 用户ID
     * @param tOrder
     * @return
     */
    @RequestMapping("newHouse")
    public JsonResult<List<TOrder>> newOrderList(QueryRequest queryRequest, TOrder tOrder) {
        if (queryRequest.getPageNum() == 1) {
            QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
            queryWrapper.ne("USER_ID", 0);
            queryWrapper.lt("PERCENTAGE", 1.00);
            queryWrapper.orderByDesc("PERCENTAGE");
            Page<TOrder> page = new Page<>(1, 3);
            page = orderService.page(page, queryWrapper);
            List<TOrder> three = page.getRecords();
            queryWrapper.clear();
            Page<TOrder> pageTwo = new Page<>(1, 10);
            queryWrapper.ne("USER_ID", 0);
            queryWrapper.orderByAsc("CREATE_TIME");
            pageTwo = orderService.page(pageTwo, queryWrapper);
            List<TOrder> sever = pageTwo.getRecords();
            three.addAll(sever);
            if (three.size() > 0)
                three.forEach(dao -> {
                    dao.setOWList(getTOrderWechat(dao));
                    dao.setIsHelp(isHelp(dao.getOrderId(), tOrder.getWechatIdForSelect()));
                });
            return new JsonResult<>(OK, three);
        } else {
            QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
            queryWrapper.ne("USER_ID", 0);
            queryWrapper.orderByAsc("CREATE_TIME");
            Page<TOrder> page = new Page<>(queryRequest.getPageNum(), queryRequest.getPageSize());
            page = orderService.page(page, queryWrapper);
            List<TOrder> list = page.getRecords();
            if (list.size() > 0)
                list.forEach(dao -> {
                    dao.setOWList(getTOrderWechat(dao));
                    dao.setIsHelp(isHelp(dao.getOrderId(), tOrder.getWechatIdForSelect()));
                });
            return new JsonResult<>(OK);
        }
    }

    /**
     * 砍价
     *
     * @param tOrder wechatIdForSelect 用户ID orderId 订单ID
     * @return
     */
    @RequestMapping("help")
    public JsonResult<Void> changeIsHelp(TOrder tOrder) {
        /**
         * 查询到两个 dao
         */
        TOrder dao = orderService.getById(tOrder.getOrderId());
        TWechat wechat = wechatService.getById(tOrder.getWechatIdForSelect());
        /**
         * 计算金额
         */
        Integer money = getMoney(tOrder);
        /**
         * 修改订单
         */
        dao.setHelpNum(dao.getHelpNum() + 1);
        dao.setTopMuch(dao.getTopMuch() + money);
        dao.setPercentage(dao.getTopMuch() / (dao.getTopThree() * 1.0));
        orderService.saveOrUpdate(dao);
        /**
         * 新增记录表
         */
        TOrderWechat orderWechat = new TOrderWechat();
        orderWechat.setOrderId(dao.getOrderId());
        orderWechat.setWechatId(wechat.getWechatId());
        orderWechat.setAva(wechat.getAva());
        orderWechat.setNickName(wechat.getNickName());
        orderWechat.setMoney(money);
        orderWechat.setCreateTime(new Date());
        orderWechat.setModifyTime(new Date());
        orderWechatService.save(orderWechat);
        /**
         * 积分
         */
        changeScore(wechat.getScoreId());
        return new JsonResult<>(OK);
    }


    /**
     * 砍价列表记录
     *
     * @param orderWechat orderId 订单ID 或  wechatId 用户Id
     * @return
     */
    @RequestMapping("orderRecord")
    public JsonResult<List<TOrderWechat>> getTOrderWechatList(TOrderWechat orderWechat) {
        QueryWrapper<TOrderWechat> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(orderWechat);
        return new JsonResult<>(OK, orderWechatService.list(queryWrapper));
    }

    /**
     * 添加订单
     *
     * @return
     */
    @RequestMapping("add")
    public JsonResult<Void> addOrder(TOrder tOrder) {
        return new JsonResult<>(OK);
    }

    /**
     * 加积分
     *
     * @param scoreId
     */
    private void changeScore(Long scoreId) {
        TScore score = scoreService.getById(scoreId);
        score.setSum(score.getSum() + 10);
        scoreService.saveOrUpdate(score);
        TScoreRecord scoreRecord = new TScoreRecord();
        scoreRecord.setScoreId(scoreId);
        scoreRecord.setUpDown(0);
        scoreRecord.setNum(10);
        scoreRecord.setCreateTime(new Date());
        scoreRecord.setModifyTime(new Date());
        scoreRecordService.save(scoreRecord);
    }

    private Integer getMoney(TOrder tOrder) {
        //只有一个名额
        if (tOrder.getTopThreeNum() - tOrder.getHelpNum() == 1) {
            return tOrder.getTopThree() - tOrder.getTopMuch();
        }
        Integer money = tOrder.getTopThree() - tOrder.getTopMuch();
        Integer people = tOrder.getTopThreeNum() - tOrder.getHelpNum();
        Integer ret = money / people;
        if (ret <= 100) {
            return ret;
        }
        Integer min = ret - 100;
        Integer max = ret + 100;
        return new Random().nextInt((max - min) + min);
    }

    /**
     * 用户ID 订单ID
     *
     * @param
     * @return
     */
    private Integer isHelp(Long orderId, Long wechatId) {
        QueryWrapper<TOrderWechat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("WECHAT_ID", wechatId);
        queryWrapper.eq("ORDER_ID", orderId);
        List<TOrderWechat> list = orderWechatService.list(queryWrapper);
        //有记录 返回1
        if (list.size() > 0) {
            return 1;
        }
        //没有记录返回0
        return 0;
    }

    /**
     * 得到映射
     *
     * @param tOrder
     * @return
     */
    private List<TOrderWechat> getTOrderWechat(TOrder tOrder) {
        QueryWrapper<TOrderWechat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ORDER_ID", tOrder.getOrderId());
        queryWrapper.orderByDesc("CREATE_TIME");
        Page<TOrderWechat> page = new Page<>(1, 10);
        page = orderWechatService.page(page, queryWrapper);
        return page.getRecords();
    }


}

