package com.ilia.leek.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.ilia.leek.common.enums.ResultCode;
import com.ilia.leek.common.exception.BaseBusinessException;
import com.ilia.leek.entity.Fund;
import com.ilia.leek.util.fund.FundAndCompanyRequestInterface;
import com.ilia.leek.util.fund.FundConstant;
import com.ilia.leek.util.fund.eastmoney.HttpFundgzFundUtil;
import com.ilia.leek.util.fund.xiong.DoctorXiongResponse;
import com.ilia.leek.util.fund.xiong.DoctorXiongUtil;
import com.ilia.leek.util.fund.xiong.HttpDoctorXiongFundUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author Alice on 2021/4/26
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
public class FundAndCompanyHandler implements FundAndCompanyRequestInterface {

    @Override
    public List<Fund> getAllFund() {
        DoctorXiongResponse res = HttpDoctorXiongFundUtil.getAllFund();
        List<Fund> list = new ArrayList<>();
        if (HttpDoctorXiongFundUtil.DOCTORXIONG_SUCCESS.equals(res.getCode())) {
            JSONArray jsonArray = JSONUtil.parseArray(res.getData());
            jsonArray.stream().iterator().forEachRemaining(ele -> {
                Fund fund = DoctorXiongUtil.resolveListFund((JSONArray) ele);
                list.add(fund);
            });
        }
        return list.size() > 0 ? list : null;
    }

    @Override
    public Fund getRealTimeFundByCode(String code) {
        if (ObjectUtil.isEmpty(code) || code.length() != FundConstant.FUND_CODE_LENGTH) {
            throw new BaseBusinessException(ResultCode.PARAMETER_ERROR);
        }
        try {
            DoctorXiongResponse res = HttpDoctorXiongFundUtil.getDetail(code);
            if (HttpDoctorXiongFundUtil.DOCTORXIONG_SUCCESS.equals(res.getCode())) {
                Fund fund = DoctorXiongUtil.createFundByDoctorXiong(res.getData());
                if (null != fund && Objects.equals(code, fund.getFundCode())) {
                    return fund;
                }
                log.info("解析fund失败.");
                return null;
            }
        } catch (BaseBusinessException exception) {
            //BaseBusinessException
            String result = HttpFundgzFundUtil.getRealTime(code);
            log.info(result);
        }
        log.info("get fund failed");
        return null;
    }

    @Override
    public List<Fund> getDetailByCodes(List<String> codes) {
        if (ObjectUtil.isEmpty(codes)) {
            throw new BaseBusinessException(ResultCode.PARAMETER_ERROR);
        }
        StringBuilder codeString = new StringBuilder();
        for (int i = 0; i < codes.size(); i++) {
            if (codes.get(i).length() == FundConstant.FUND_CODE_LENGTH) {
                codeString.append(codes.get(i));
                if (i < codes.size() - 1) {
                    codeString.append(",");
                }
            }
        }
        try {
            DoctorXiongResponse res = HttpDoctorXiongFundUtil.getFund(codeString.toString());
            if (HttpDoctorXiongFundUtil.DOCTORXIONG_SUCCESS.equals(res.getCode())) {
                JSONArray jsonArray = JSONUtil.parseArray(res.getData());
                List<Fund> list = new ArrayList<>();
                jsonArray.stream().iterator().forEachRemaining(ele -> {
                    Fund fund = DoctorXiongUtil.createFundByDoctorXiong(ele.toString());
                    list.add(fund);
                });
                return list;
            }
        } catch (BaseBusinessException exception) {
            //BaseBusinessException

        }
        return null;
    }

    @Override
    public List<Fund> getRankFund() {
        return null;
    }
}
