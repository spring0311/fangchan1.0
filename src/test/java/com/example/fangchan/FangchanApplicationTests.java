package com.example.fangchan;

import com.example.fangchan.app.entity.TOrder;
import com.example.fangchan.app.entity.TOrderWechat;
import com.example.fangchan.app.entity.TWechat;
import com.example.fangchan.until.BaseController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
class FangchanApplicationTests extends BaseController {

    @Test
    void contextLoads() {
    }

    @Test
    void money() {
        Double i = 300.07;
        Integer j = 14;
        Double k = i / j;
        System.err.println(k);
    }

    @Test
    void moneyOne() {
        TOrder dao = orderService.getById(18);
        TWechat wechat = wechatService.getById(5);
        for (int i = 0; i < 100; i++) {
            /**
             * 计算金额
             */
            Integer money = getMoney(dao);
            System.err.println("money:" + money);
            /**
             * 修改订单
             */
            dao.setHelpNum(dao.getHelpNum() + 1);
            dao.setTopMuch(dao.getTopMuch() + money);
            dao.setPercentage(dao.getTopMuch() / (dao.getTopThree() * 1.0000));
            dao.setModifyTime(new Date());
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
        }

    }


    private Integer getMoney(TOrder tOrder) {
        Integer money = 0;
        Integer people = 0;
        if (tOrder.getHelpNum() >= tOrder.getTopTwoNum()) {
            //砍价人数 > 二阶段人数 进入第三阶段砍价
            money = tOrder.getTopThree() - tOrder.getTopMuch();
            people = tOrder.getTopThreeNum() - tOrder.getHelpNum();
        } else if (tOrder.getHelpNum() >= tOrder.getTopOneNum()) {
            //砍价人数 > 一阶段人数 进入二阶段砍价
            money = tOrder.getTopTwo() - tOrder.getTopMuch();
            people = tOrder.getTopTwoNum() - tOrder.getHelpNum();
        } else {
            //砍价人数 < 一阶段人数 进入一阶段砍价
            //一阶段剩余金钱
            money = tOrder.getTopOne() - tOrder.getTopMuch();
            //一阶段剩余人数
            people = tOrder.getTopOneNum() - tOrder.getHelpNum();
        }
        if (people == 1) {
            return money;
        }
        //得到平均数
        Integer ret = money / people;
        //浮动范围为20
        if (ret <= 20) {
            return ret;
        }
        Integer min = ret - 20;
        Integer max = ret + 20;
        int num = (int) (Math.random() * (max - min + 1) + min);
        return num;
    }

}
