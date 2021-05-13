package com.ilia.leek.util.fund;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alice on 2021/4/26
 * @version 1.0
 * @since 1.0
 * 基金相关常量
 */
@Configuration
public class FundConstant {

    /**
     * 基金代码固定长度
     */
    public static final int FUND_CODE_LENGTH = 6;

    /**
     * 基金详情更新时距
     */
    public static long TIME_OUT = 1000 * 60;

    /**
     * 基金详情更新时距
     */
    public static String CRON = "0 */10 * * * ?";

    /**
     * 小熊同学
     * 获取基金详情
     */
    public static String DOCTORXIONG_DETAIL = "";

    /**
     * 小熊同学
     * 获取基金详情
     */
    public static String DOCTORXIONG_FUND = "";

    /**
     * 天天基金网
     * 基金实时信息
     */
    public static String FUNDGZ_REAL_TIME = "";

    @Value("${fund.timeout}")
    public void setTimeOut(long timeOut) {
        TIME_OUT = timeOut;
    }

    @Value("${fund.cron}")
    public void setCRON(String CRON) {
        FundConstant.CRON = CRON;
    }

    @Value("${doctorxiong.detail}")
    private void setDoctorxiongDetail(String doctorxiongDetail) {
        DOCTORXIONG_DETAIL = doctorxiongDetail;
    }

    @Value("${doctorxiong.fund}")
    public void setDoctorxiongFund(String doctorxiongFund) {
        DOCTORXIONG_FUND = doctorxiongFund;
    }

    @Value("${fundgz.real_time}")
    private void setFundgzRealTime(String fundgzRealTime) {
        FUNDGZ_REAL_TIME = fundgzRealTime;
    }
}
