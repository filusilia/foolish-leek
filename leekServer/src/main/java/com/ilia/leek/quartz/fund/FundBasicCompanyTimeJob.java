package com.ilia.leek.quartz.fund;

import com.ilia.leek.service.FundCompanyService;
import com.ilia.leek.util.fund.FundAndCompanyRequestInterface;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 基金基础公司信息更新定时任务
 * @author Alice on 2022/2/18
 * @version 1.0
 * @since 1.0
 */
@Slf4j
public class FundBasicCompanyTimeJob extends QuartzJobBean {

    @Autowired
    private FundCompanyService fundCompanyService;
    @Autowired
    private FundAndCompanyRequestInterface fundAndCompanyHandler;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("定时任务启动,获取所有基金公司");
    }
}
