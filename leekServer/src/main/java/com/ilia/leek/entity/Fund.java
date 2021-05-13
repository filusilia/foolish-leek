package com.ilia.leek.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Alice on 2021/5/12
 * @version 1.0
 * @since 1.0
 * 基金表
 */
@Data
@TableName(value = "leek.ft_fund")
public class Fund implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 基金code
     */
    @TableField(value = "fund_code")
    private String fundCode;

    /**
     * 基金名称
     */
    @TableField(value = "fund_name")
    private String fundName;

    /**
     * 基金拼音简称
     */
    @TableField(value = "short_name")
    private String shortName;

    /**
     * 基金全称
     */
    @TableField(value = "fund_full_pinyin")
    private String fundFullPinyin;

    /**
     * 基金类型
     */
    @TableField(value = "fund_type")
    private String fundType;

    /**
     * 当前基金单位净值
     */
    @TableField(value = "net_worth")
    private BigDecimal netWorth;

    /**
     * 当前基金单位净值估算
     */
    @TableField(value = "expect_worth")
    private BigDecimal expectWorth;

    /**
     * 当前基金累计净值
     */
    @TableField(value = "total_worth")
    private BigDecimal totalWorth;

    /**
     * 当前基金单位净值估算日涨幅,单位为百分比
     */
    @TableField(value = "expect_growth")
    private String expectGrowth;

    /**
     * 单位净值日涨幅,单位为百分比
     */
    @TableField(value = "day_growth")
    private String dayGrowth;

    /**
     * 单位净值周涨幅,单位为百分比
     */
    @TableField(value = "last_week_growth")
    private String lastWeekGrowth;

    /**
     * 单位净值月涨幅,单位为百分比
     */
    @TableField(value = "last_month_growth")
    private String lastMonthGrowth;

    /**
     * 单位净值三月涨幅,单位为百分比
     */
    @TableField(value = "last_three_months_growth")
    private String lastThreeMonthsGrowth;

    /**
     * 单位净值六月涨幅,单位为百分比
     */
    @TableField(value = "last_six_months_growth")
    private String lastSixMonthsGrowth;

    /**
     * 单位净值年涨幅,单位为百分比
     */
    @TableField(value = "last_year_growth")
    private String lastYearGrowth;

    /**
     * 最小申购金额
     */
    @TableField(value = "buy_min")
    private BigDecimal buyMin;

    /**
     * 原始买入费率,单位为百分比
     */
    @TableField(value = "buy_source_rate")
    private BigDecimal buySourceRate;

    /**
     * 现费率
     */
    @TableField(value = "fund_rate")
    private BigDecimal fundRate;

    /**
     * 基金经理
     */
    @TableField(value = "manager")
    private String manager;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fund fund = (Fund) o;
        return Objects.equals(fundCode, fund.fundCode) && Objects.equals(fundName, fund.fundName) && Objects.equals(shortName, fund.shortName) && Objects.equals(fundFullPinyin, fund.fundFullPinyin) && Objects.equals(fundType, fund.fundType) && Objects.equals(netWorth, fund.netWorth) && Objects.equals(expectWorth, fund.expectWorth) && Objects.equals(totalWorth, fund.totalWorth) && Objects.equals(expectGrowth, fund.expectGrowth) && Objects.equals(dayGrowth, fund.dayGrowth) && Objects.equals(lastWeekGrowth, fund.lastWeekGrowth) && Objects.equals(lastMonthGrowth, fund.lastMonthGrowth) && Objects.equals(lastSixMonthsGrowth, fund.lastSixMonthsGrowth) && Objects.equals(lastYearGrowth, fund.lastYearGrowth) && Objects.equals(buyMin, fund.buyMin) && Objects.equals(buySourceRate, fund.buySourceRate) && Objects.equals(fundRate, fund.fundRate) && Objects.equals(manager, fund.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fundCode, fundName, shortName, fundFullPinyin, fundType, netWorth, expectWorth, totalWorth, expectGrowth, dayGrowth, lastWeekGrowth, lastMonthGrowth, lastSixMonthsGrowth, lastYearGrowth, buyMin, buySourceRate, fundRate, manager);
    }

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_FUND_CODE = "fund_code";

    public static final String COL_FUND_NAME = "fund_name";

    public static final String COL_SHORT_NAME = "short_name";

    public static final String COL_FUND_FULL_PINYIN = "fund_full_pinyin";

    public static final String COL_FUND_TYPE = "fund_type";

    public static final String COL_NET_WORTH = "net_worth";

    public static final String COL_EXPECT_WORTH = "expect_worth";

    public static final String COL_TOTAL_WORTH = "total_worth";

    public static final String COL_EXPECT_GROWTH = "expect_growth";

    public static final String COL_DAY_GROWTH = "day_growth";

    public static final String COL_LAST_WEEK_GROWTH = "last_week_growth";

    public static final String COL_LAST_MONTH_GROWTH = "last_month_growth";

    public static final String COL_LAST_THREE_MONTHS_GROWTH = "last_three_months_growth";

    public static final String COL_LAST_SIX_MONTHS_GROWTH = "last_six_months_growth";

    public static final String COL_LAST_YEAR_GROWTH = "last_year_growth";

    public static final String COL_BUY_MIN = "buy_min";

    public static final String COL_BUY_SOURCE_RATE = "buy_source_rate";

    public static final String COL_FUND_RATE = "fund_rate";

    public static final String COL_MANAGER = "manager";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}