package com.ilia.leek.controller;

import cn.hutool.core.util.ObjectUtil;
import com.ilia.leek.common.enums.ResultCode;
import com.ilia.leek.common.result.ResultResponse;
import com.ilia.leek.entity.pojo.QueryFund;
import com.ilia.leek.service.FundService;
import com.ilia.leek.service.UserFundService;
import com.ilia.leek.util.fund.FundConstant;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alice on 2021/4/23
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("fund")
public class FundController {

    private final FundService fundService;
    private final UserFundService userFundService;

    public FundController(FundService fundService, UserFundService userFundService) {
        this.fundService = fundService;
        this.userFundService = userFundService;
    }

    /**
     * 添加我的基金
     *
     * @param queryFund
     * @return ResultResponse
     */
    @RequestMapping("addMyFund")
    public ResultResponse<Object> addMyFund(@RequestBody QueryFund queryFund) {
        if (ObjectUtil.hasEmpty(queryFund, queryFund.getFundCode())) {
            return ResultResponse.failed(ResultCode.PARAMETER_NULL);
        }
        return userFundService.addMyFund(queryFund);
    }

    /**
     * 查询基金实时信息
     *
     * @param queryFund
     * @return ResultResponse
     */
    @RequestMapping("realTimeFund")
    public ResultResponse<Object> realTimeFund(@RequestBody QueryFund queryFund) {
        if (ObjectUtil.hasEmpty(queryFund)) {
            return ResultResponse.failed(ResultCode.PARAMETER_NULL);
        }
        return fundService.realTimeFundByCode(queryFund.getFundCode());
    }

    /**
     * 分页查询我的基金
     *
     * @param queryFund
     * @return ResultResponse
     */
    @RequestMapping("listFund")
    public ResultResponse<Object> listFund(@RequestBody QueryFund queryFund) {
        if (ObjectUtil.hasEmpty(queryFund)) {
            return ResultResponse.failed(ResultCode.PARAMETER_NULL);
        }
        return userFundService.getMyFunds(queryFund);
    }

    /**
     * @param queryFund
     * @return ResultResponse
     */
    @RequestMapping("realTimeFundByCode")
    public ResultResponse<Object> realTimeFundByCode(@RequestBody QueryFund queryFund) {
        if (ObjectUtil.hasEmpty(queryFund, queryFund.getFundCode())) {
            return ResultResponse.failed(ResultCode.PARAMETER_NULL);
        }
        if (queryFund.getFundCode().length() != FundConstant.FUND_CODE_LENGTH) {
            return ResultResponse.failed(ResultCode.PARAMETER_ERROR);
        }
        return fundService.realTimeFundByCode(queryFund.getFundCode());
    }
}
