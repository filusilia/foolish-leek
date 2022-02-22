package com.ilia.leek.quartz.fund;

import com.ilia.leek.entity.Fund;
import com.ilia.leek.service.FundCompanyService;
import com.ilia.leek.service.FundService;
import com.ilia.leek.util.fund.FundAndCompanyRequestInterface;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * 基金基础信息更新定时任务
 *
 * @author Alice on 2022/2/18
 * @version 1.0
 * @since 1.0
 */
@Slf4j
public class FundBasicTimeJob extends QuartzJobBean {

    @Autowired
    private FundCompanyService fundCompanyService;
    @Autowired
    private FundService fundService;
    @Autowired
    private FundAndCompanyRequestInterface fundAndCompanyHandler;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("定时任务启动,获取所有基金");
        List<Fund> fundList = fundAndCompanyHandler.getAllFund();
    }
}
