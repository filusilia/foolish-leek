package com.ilia.leek.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ilia.leek.common.enums.ResultCode;
import com.ilia.leek.common.exception.BaseBusinessException;
import com.ilia.leek.common.result.ResultResponse;
import com.ilia.leek.entity.Fund;
import com.ilia.leek.mapper.FundMapper;
import com.ilia.leek.util.CustomBeanUtil;
import com.ilia.leek.util.fund.FundAndCompanyRequestInterface;
import com.ilia.leek.util.fund.FundConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author Alice on 2021/4/20
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
public class FundService extends ServiceImpl<FundMapper, Fund> {

    private final FundAndCompanyRequestInterface fundAndCompanyHandler;

    public FundService(@Qualifier("fundAndCompanyHandler") FundAndCompanyRequestInterface fundAndCompanyHandler) {
        this.fundAndCompanyHandler = fundAndCompanyHandler;
    }

    public ResultResponse<Object> realTimeFundByCode(String code) {
        LambdaQueryWrapper<Fund> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Fund::getFundCode, code);
        Fund fund = getOne(wrapper);
        if (ObjectUtil.isEmpty(fund)) {
            return ResultResponse.failed(ResultCode.SEARCH_NULL);
        }
        //超时TIME_OUT后直接重新请求网络
        if (ObjectUtil.isEmpty(fund.getUpdateTime()) ||
                Duration.between(fund.getUpdateTime(), LocalDateTime.now()).toMillis() > FundConstant.TIME_OUT) {
            Fund newFund = fundAndCompanyHandler.getRealTimeFundByCode(code);
            if (ObjectUtil.isEmpty(newFund)) {
                return ResultResponse.failed(ResultCode.NET_ERROR);
            }
            //更新当前基金详情到数据库
            newFund.setId(fund.getId());
            boolean success = updateById(newFund);
            if (!success) {
                log.warn("更新基金实时信息失败,基金code:{},newFund:{}", fund.getId(), newFund);
            }
            pushFund(newFund, fund);
            return ResultResponse.success(newFund);
        }
        //未超时返回数据
        return ResultResponse.success(fund);
    }

    private static void pushFund(Fund target, Fund old) {
        if (ObjectUtil.hasEmpty(target, old)) {
            throw new BaseBusinessException(ResultCode.MAKE_FALSE);
        }
        BeanUtil.copyProperties(old, target, CustomBeanUtil.getNullPropertyNames(old));
    }
}