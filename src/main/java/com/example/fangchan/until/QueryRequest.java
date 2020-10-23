package com.example.fangchan.until;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 分压查询 条件对象
 */
@Data
@ToString
public class QueryRequest implements Serializable {

    private static final long serialVersionUID = -6507237778470420328L;
    /**
     * 当前页面数据量
     */
    private int pageSize = 10;

    /**
     * 当前页码
     */
    private int pageNum = 1;

    /**
     * 排序字段
     */
    private String field;

    /**
     * 排序规则，asc升序，desc降序
     */
    private String order;

}
