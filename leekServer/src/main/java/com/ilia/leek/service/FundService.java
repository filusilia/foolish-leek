package com.ilia.leek.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ilia.leek.common.enums.ResultCode;
import com.ilia.leek.common.result.ResultResponse;
import com.ilia.leek.entity.Fund;
import com.ilia.leek.entity.model.BaseResultModel;
import com.ilia.leek.entity.model.ResultUserFund;
import com.ilia.leek.entity.pojo.QueryFund;
import com.ilia.leek.mapper.FundMapper;
import com.ilia.leek.util.CustomBeanUtil;
import com.ilia.leek.util.fund.FundAndCompanyRequestInterface;
import com.ilia.leek.util.fund.FundConstant;
import lombok.extern.slf4j.Slf4j;
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

    public FundService(FundAndCompanyRequestInterface fundAndCompanyHandler) {
        this.fundAndCompanyHandler = fundAndCompanyHandler;
    }


    /**
     * 搜索基金
     *
     * @param queryFund 查询基金
     * @return {@link ResultResponse<Object>}
     */
    public ResultResponse<Object> searchFunds(QueryFund queryFund) {
        if (ObjectUtil.isAllEmpty(queryFund, queryFund.getFundCode(),
                queryFund.getFundName(), queryFund.getPinyin(), queryFund.getFundType())) {
            return ResultResponse.failed(ResultCode.SEARCH_NOT_ALLOWED);
        }
        Page<ResultUserFund> page = PageHelper.startPage(queryFund.getPage(), queryFund.getPageSize())
                .doSelectPage(() -> searchFunds(queryFund));
        BaseResultModel<ResultUserFund> resultModel = new BaseResultModel<>();
        resultModel.setTotal(page.getTotal());
        resultModel.setData(page.getResult());
        return ResultResponse.success(resultModel);
    }


    /**
     * 查询基金实时信息
     *
     * @param code 代码
     * @return {@link ResultResponse<Object>}
     */
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
            CustomBeanUtil.pushParam(newFund, fund);
            return ResultResponse.success(newFund);
        }
        //未超时返回数据
        return ResultResponse.success(fund);
    }
}
