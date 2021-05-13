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
                .withIdentity("fundJobDetail", JOB_GET_FUND_DETAIL)
                .usingJobData("userName", "pan_junbiao的博客")
                .storeDurably()
                .build();
    }

    /**
     */
    @Bean
    public Trigger fundJobTrigger() {
        //每隔5秒执行一次
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(FundConstant.CRON);
        //创建触发器
        //关联上述的JobDetail
        return TriggerBuilder.newTrigger()
                .forJob(fundRealTimeJobDetail())
                .withIdentity("fundJobTrigger", TRIGGER_GET_FUND_DETAIL)
                .withSchedule(cronScheduleBuilder)
                .build();
    }

}
