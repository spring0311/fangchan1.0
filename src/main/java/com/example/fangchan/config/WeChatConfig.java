package com.example.fangchan.config;

import com.github.wxpay.sdk.WXPayConfig;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 微信小程序配置类
 */
@Configuration//配置类
public class WeChatConfig implements WXPayConfig {

    private byte[] certData;


    //@Value("${appId.value}")
    private String appIdValue = "wx92ccfd81dcf1434f";
    //@Value("${mchId.value}")
    private String mchIdValue = "1595542641";
    //@Value("${key.value}")
    private String keyValue = "202cb962ac59075b964b07152d234b70";

    public String getAppID() {
        return appIdValue;
    }

    public String getMchID() {
        return mchIdValue;
    }

    public String getKey() {
        return keyValue;
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

}
