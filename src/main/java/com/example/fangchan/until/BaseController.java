package com.example.fangchan.until;

import com.example.fangchan.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.util.*;

/**
 * 控制层基类
 *
 * @author: weiZiHao
 * @create: 2020-08-20 16:01
 */
public class BaseController {

    /**
     * 微信
     */
    @Value("${user.appSecret}")
    public String userAppSecret;

    @Value("${user.appId}")
    public String userAppId;

    @Value("${user.orderName}")
    public String orderName;


    /**
     * 返回状态码
     */
    public static final String OK = "2000";
    /**
     * redis
     */
    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 商品
     */
    @Autowired
    public TCommService commService;

    /**
     * 订单
     */
    @Autowired
    public TOrderService orderService;

    /**
     * 订单用户映射
     */
    @Autowired
    public TOrderWechatService orderWechatService;

    /**
     * 积分
     */
    @Autowired
    public TScoreService scoreService;

    /**
     * 积分记录
     */
    @Autowired
    public TScoreRecordService scoreRecordService;

    /**
     * 用户
     */
    @Autowired
    public TWechatService wechatService;

    /**
     * 默认订单数值
     */
    @Autowired
    public TTopService tTopService;

    /**
     * 获得盐值
     *
     * @return 盐值, 永久层数据
     */
    public static String getSalt() {
        return UUID.randomUUID().toString();
    }

    /**
     * 密码加密
     *
     * @param password
     * @param salt
     * @return 加密后密码, 永久层数据
     */
    public static String changePassword(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes());
        }
        return password;
    }

    /**
     * 八位随机码
     *
     * @return
     */
    public static String genRandomNum() {
        int maxNum = 36;
        int i;
        int count = 0;
        char[] str = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < 8) {
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }


    /**
     * 微信支付签名算法sign
     *
     * @param parameters
     * @return
     */
    public static String createSign(SortedMap<String, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }

        sb.append("key=" + "202cb962ac59075b964b07152d234b70");
        System.out.println("签名字符串:" + sb.toString());
//       System.out.println("签名MD5未变大写：" + MD5Util.MD5Encode(sb.toString(), characterEncoding));
        String sign = md5Password(sb.toString()).toUpperCase();
        return sign;
    }

    /**
     * 生成32位md5码
     *
     * @param key
     * @return
     */
    public static String md5Password(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 时间偏移量
     *
     * @return
     */
    public static Long getTimeId() {
        return System.currentTimeMillis();
    }

}
