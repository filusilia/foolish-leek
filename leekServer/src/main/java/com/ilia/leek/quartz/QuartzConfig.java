package com.ilia.leek.quartz;

import com.ilia.leek.quartz.fund.FundJobConfig;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.PreDestroy;
import java.util.Date;

/**
 * @author Alice on 2021/5/12
 * @version 1.0
 * @since 1.0
 */

@Configuration
@Slf4j
public class QuartzConfig {


    private JobFactory jobFactory;

    @Autowired
    private FundJobConfig fundJobConfig;

    /**
     * @param jobFactory
     * @author jinhaoxun
     * @description 构造器
     */
    public QuartzConfig(JobFactory jobFactory) {
        this.jobFactory = jobFactory;
    }

    @Bean
    public void initScheduleJob() {
        try {
            Scheduler scheduler = scheduler();
            scheduler.start();
            JobDetail jobDetail = fundJobConfig.fundRealTimeJobDetail();
            log.info("job build success.");
            log.info("ScheduleJob init first .");
            Date date = scheduler.scheduleJob(jobDetail, fundJobConfig.fundJobTrigger());
        } catch (SchedulerException e) {
            log.error("init ScheduleJob error ,error message :{}", e.getMessage());
        }
    }

    /**
     * @return SchedulerFactoryBean
     * @author jinhaoxun
     * @description 配置SchedulerFactoryBean，将一个方法产生为Bean并交给Spring容器管理
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        log.info("开始注入定时任务调度器工厂...");
        // Spring提供SchedulerFactoryBean为Scheduler提供配置信息,并被Spring容器管理其生命周期
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // 设置自定义Job Factory，用于Spring管理Job bean
        factory.setJobFactory(jobFactory);
        log.info("注入定时任务调度器工厂成功！");
        return factory;
    }

    @Bean(name = "scheduler")
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }

    @PreDestroy
    public void des() {
        log.info("destroy now .");
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.shutdown();
            log.info("shutdown scheduler success.");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
