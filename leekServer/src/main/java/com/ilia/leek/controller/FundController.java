package com.ilia.leek.controller;

import cn.hutool.core.util.ObjectUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.ilia.leek.common.enums.ResultCode;
import com.ilia.leek.common.result.ResultResponse;
import com.ilia.leek.entity.pojo.QueryFund;
import com.ilia.leek.service.FundService;
import com.ilia.leek.service.UserFundService;
import com.ilia.leek.util.fund.FundConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alice on 2021/4/23
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("fund")
@Api(tags = "基金模块")
@ApiSupport(author = "ilia")
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
     * @param queryFund 查询基金
     * @return {@link ResultResponse<Object>}
     */
    @PostMapping("addMyFund")
    @ApiOperation(value = "添加我的基金")
    @ApiOperationSupport(
            includeParameters = {
                    "queryFund.userId", "queryFund.favorite",
                    "queryFund.order", "queryFund.fundCode"},
            ignoreParameters = {
                    "fundName", "pinyin",
                    "fundType", "companyCode", "companyName",
                    "page", "pageSize"})
    public ResultResponse<Object> addMyFund(@RequestBody QueryFund queryFund) {
        if (ObjectUtil.hasEmpty(queryFund, queryFund.getFundCode())) {
            return ResultResponse.failed(ResultCode.PARAMETER_NULL);
        }
        return userFundService.addMyFund(queryFund);
    }


    /**
     * 解绑基金
     *
     * @param queryFund 查询基金
     * @return {@link ResultResponse<Object>}
     */
    @PostMapping("unlockMyFund")
    @ApiOperation(value = "解绑基金")
    @ApiOperationSupport(
            includeParameters = {
                    "queryFund.userId", "queryFund.fundCode"})
    public ResultResponse<Object> unlockMyFund(@RequestBody QueryFund queryFund) {
        if (ObjectUtil.hasEmpty(queryFund, queryFund.getFundCode())) {
            return ResultResponse.failed(ResultCode.PARAMETER_NULL);
        }
        return userFundService.unlockMyFund(queryFund);
    }

    /**
     * 最喜欢的基金
     *
     * @param queryFund 查询基金
     * @return ResultResponse
     */
    @PostMapping("favoriteFund")
    @ApiOperation(value = "收藏基金")
    @ApiOperationSupport(
            includeParameters = {
                    "queryFund.userId", "queryFund.fundCode"})
    public ResultResponse<Object> favoriteFund(@RequestBody QueryFund queryFund) {
        if (ObjectUtil.hasEmpty(queryFund, queryFund.getFundCode())) {
            return ResultResponse.failed(ResultCode.PARAMETER_NULL);
        }
        return userFundService.favoriteFund(queryFund);
    }

    /**
     * 搜索基金
     *
     * @param queryFund 查询基金
     * @return {@link ResultResponse<Object>}
     */
    @GetMapping("searchFund")
    @ApiOperation(value = "搜索基金")
    public ResultResponse<Object> searchFund(@RequestBody QueryFund queryFund) {
        if (ObjectUtil.hasEmpty(queryFund)) {
            return ResultResponse.failed(ResultCode.PARAMETER_NULL);
        }
        return fundService.searchFunds(queryFund);
    }

    /**
     * 分页查询基金
     *
     * @param queryFund 查询基金
     * @return {@link ResultResponse<Object>}
     */
    @GetMapping("listFund")
    @ApiOperation(value = "分页查询基金")
    public ResultResponse<Object> listFund(@RequestBody QueryFund queryFund) {
        if (ObjectUtil.hasEmpty(queryFund)) {
            return ResultResponse.failed(ResultCode.PARAMETER_NULL);
        }
        return userFundService.getMyFunds(queryFund);
    }

    /**
     * 查询基金实时信息
     *
     * @param queryFund 查询基金
     * @return {@link ResultResponse<Object>}
     */
    @GetMapping("realTimeFundByCode")
    @ApiOperation(value = "查询基金实时信息")
    @ApiOperationSupport(
            includeParameters = {
                    "queryFund.userId", "queryFund.fundCode"})
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
