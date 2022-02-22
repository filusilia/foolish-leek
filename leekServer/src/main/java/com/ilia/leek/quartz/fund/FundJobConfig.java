package com.ilia.leek.quartz.fund;

import com.ilia.leek.util.fund.FundConstant;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alice on 2021/5/13
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class FundJobConfig {

    private static final String JOB_GET_FUND_DETAIL = "JOB_GET_FUND_DETAIL";
    private static final String TRIGGER_GET_FUND_DETAIL = "TRIGGER_GET_FUND_DETAIL";

    /**
     * 定时任务1：
     * 同步用户信息Job（任务详情）
     */
    @Bean
    public JobDetail fundRealTimeJobDetail() {
        return JobBuilder.newJob(FundRealTimeJob.class)
                .withIdentity(JOB_GET_FUND_DETAIL, "fundJobDetail")
                .storeDurably()
                .build();
    }

    /**
     *
     */
    @Bean
    public Trigger fundJobTrigger() {
        //CRON执行
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(FundConstant.CRON);
        //创建触发器
        //关联上述的JobDetail
        return TriggerBuilder.newTrigger()
                .forJob(fundRealTimeJobDetail())
                .withIdentity(TRIGGER_GET_FUND_DETAIL, "fundJobTrigger")
                .withSchedule(cronScheduleBuilder)
                .build();
    }

    private static final String TRIGGER_BASIC_FUND = "TRIGGER_BASIC_FUND";

    /**
     * 定时任务：
     * 获取全部基金
     */
    @Bean
    public JobDetail fundBasicJobDetail() {
        return JobBuilder.newJob(FundBasicTimeJob.class)
                .withIdentity(TRIGGER_BASIC_FUND, "fundBasicJobDetail")
                .storeDurably()
                .build();
    }

    /**
     * 基金基础信息
     *
     * @return Trigger
     */
    @Bean
    public Trigger fundBasicJobTrigger() {
        //CRON执行
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(FundConstant.BASIC_CRON);
        //创建触发器
        //关联上述的JobDetail
        return TriggerBuilder.newTrigger()
                .forJob(fundBasicJobDetail())
                .withIdentity(TRIGGER_BASIC_FUND, "fundBasicTrigger")
                .withSchedule(cronScheduleBuilder)
                .build();
    }

    private static final String TRIGGER_BASIC_COMPANY = "TRIGGER_BASIC_COMPANY";

    /**
     * 定时任务：
     * 获取全部基金公司
     */
    @Bean
    public JobDetail fundBasicCompanyJobDetail() {
        return JobBuilder.newJob(FundBasicCompanyTimeJob.class)
                .withIdentity(TRIGGER_BASIC_COMPANY, "fundBasicJobDetail")
                .storeDurably()
                .build();
    }

    /**
     * 基金公司
     *
     * @return Trigger
     */
    @Bean
    public Trigger fundBasicCompanyJobTrigger() {
        //CRON执行
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(FundConstant.BASIC_CRON);
        //创建触发器
        //关联上述的JobDetail
        return TriggerBuilder.newTrigger()
                .forJob(fundBasicCompanyJobDetail())
                .withIdentity(TRIGGER_BASIC_COMPANY, "fundBasicTrigger")
                .withSchedule(cronScheduleBuilder)
                .build();
    }

}
