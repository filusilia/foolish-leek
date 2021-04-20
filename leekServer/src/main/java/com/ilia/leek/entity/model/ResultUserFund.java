package com.ilia.leek.entity.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Alice on 2021/4/26
 * @version 1.0
 * @since 1.0
 */
public class ResultUserFund {
    /**
     * 主键
     */
    private Long id;

    /**
     * 是否收藏
     * 0- 未收藏
     * 1- 收藏
     */
    private String favorite;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 基金code
     */
    private String fundCode;

    /**
     * 基金名称
     */
    private String fundName;

    /**
     * 基金拼音简称
     */
    private String shortName;

    /**
     * 基金全称
     */
    private String fundFullPinyin;

    /**
     * 基金类型
     */
    private String fundType;

    /**
     * 当前基金单位净值
     */
    private BigDecimal netWorth;

    /**
     * 当前基金单位净值估算
     */
    private BigDecimal expectWorth;

    /**
     * 当前基金累计净值
     */
    private BigDecimal totalWorth;

    /**
     * 当前基金单位净值估算日涨幅,单位为百分比
     */
    private String expectGrowth;

    /**
     * 单位净值日涨幅,单位为百分比
     */
    private String dayGrowth;

    /**
     * 单位净值周涨幅,单位为百分比
     */
    private String lastWeekGrowth;

    /**
     * 单位净值月涨幅,单位为百分比
     */
    private String lastMonthGrowth;

    /**
     * 单位净值六月涨幅,单位为百分比
     */
    private String lastSixMonthsGrowth;

    /**
     * 单位净值年涨幅,单位为百分比
     */
    private String lastYearGrowth;

    /**
     * 最小申购金额
     */
    private BigDecimal buyMin;

    /**
     * 原始买入费率,单位为百分比
     */
    private BigDecimal buySourceRate;

    /**
     * 现费率
     */
    private BigDecimal fundRate;

    /**
     * 基金经理
     */
    private String manager;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
