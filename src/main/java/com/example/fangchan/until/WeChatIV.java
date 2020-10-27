package com.example.fangchan.until;

import lombok.Data;

/**
 * @author weiZiHao
 * @date 2020/9/15
 */
@Data
public class WeChatIV {

    private String encryptedData;

    private String iv;

    private String code;

}
