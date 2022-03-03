package com.ilia.leek.quartz.fund;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ilia.leek.entity.Fund;
import com.ilia.leek.mapper.UserFundMapper;
import com.ilia.leek.service.FundService;
import com.ilia.leek.util.fund.FundAndCompanyRequestInterface;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * @author Alice on 2021/5/12
 * 定时任务
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@DisallowConcurrentExecution
public class FundRealTimeJob extends QuartzJobBean {

    @Autowired
    private UserFundMapper userFundMapper;
    @Autowired
    private FundService fundService;
    @Autowired
    private FundAndCompanyRequestInterface fundAndCompanyHandler;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("定时任务启动,查询当前项目中所有激活的基金code");
        List<String> codes = userFundMapper.getFundsCodesDis();
        if (ObjectUtil.isNotEmpty(codes)) {
            log.info("查询code列表的size:{},准备进行批量请求远程功能.", codes.size());
            List<Fund> list = fundAndCompanyHandler.getDetailByCodes(codes);
            if (ObjectUtil.isNotEmpty(list)) {
                log.info("返回查询完毕,list.size: {}", list.size());
                for (Fund fund : list) {
                    LambdaUpdateWrapper<Fund> wrapper = new LambdaUpdateWrapper<>();
                    wrapper.eq(Fund::getFundCode, fund.getFundCode());
                    wrapper.set(Fund::getNetWorth, fund.getNetWorth());
                    wrapper.set(Fund::getExpectWorth, fund.getExpectWorth());
                    wrapper.set(Fund::getExpectGrowth, fund.getExpectGrowth());
                    wrapper.set(Fund::getDayGrowth, fund.getDayGrowth());
                    wrapper.set(Fund::getLastWeekGrowth, fund.getLastWeekGrowth());
                    wrapper.set(Fund::getLastMonthGrowth, fund.getLastMonthGrowth());
                    wrapper.set(Fund::getLastThreeMonthsGrowth, fund.getLastThreeMonthsGrowth());
                    wrapper.set(Fund::getLastSixMonthsGrowth, fund.getLastSixMonthsGrowth());
                    wrapper.set(Fund::getLastYearGrowth, fund.getLastYearGrowth());
                    fundService.update(fund, wrapper);
                }
                log.info("更新列表完毕.");
            }

        }
    }
}
