package com.ilia.leek.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.ilia.leek.common.enums.ResultCode;
import com.ilia.leek.common.exception.BaseBusinessException;
import com.ilia.leek.entity.Fund;
import com.ilia.leek.util.fund.FundAndCompanyRequestInterface;
import com.ilia.leek.util.fund.FundConstant;
import com.ilia.leek.util.fund.HttpFundUtil;
import com.ilia.leek.util.fund.xiong.DoctorXiongResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.ilia.leek.util.fund.HttpFundUtil.DOCTORXIONG_SUCCESS;

/**
 * @author Alice on 2021/4/26
 * @version 1.0
 * @since 1.0
 */
@Service
@Component("fundAndCompanyHandler")
@Slf4j
public class FundAndCompanyHandler implements FundAndCompanyRequestInterface {
    @Override
    public Fund getRealTimeFundByCode(String code) {
        if (ObjectUtil.isEmpty(code) || code.length() != FundConstant.FUND_CODE_LENGTH) {
            throw new BaseBusinessException(ResultCode.PARAMETER_ERROR);
        }
        //1
        try {
            DoctorXiongResponse res = HttpFundUtil.getDetail(code);
            if (DOCTORXIONG_SUCCESS.equals(res.getCode())) {
                return createFundByDoctorXiong(res.getData());
            }
        } catch (BaseBusinessException exception) {
            //BaseBusinessException
            String result = HttpFundUtil.getRealTime(code);
            log.info(result);
        }

        log.info("get fund failed");
        return null;
    }

    /**
     * 解析小熊的数据
     *
     * @param data DoctorXiongResponse.data
     * @return Fund
     */
    private static Fund createFundByDoctorXiong(String data) {
        if (ObjectUtil.isEmpty(data)) {
            return null;
        }
        return JSONUtil.toBean(data, Fund.class);
    }

    /**
     * 解析天天基金网的数据
     * ><太复杂了,有时间再写
     * @param data data
     * @return Fund
     */
    private static Fund createFundByFundgz(String data) {
        if (ObjectUtil.isEmpty(data)) {
            return null;
        }

        return JSONUtil.toBean(data, Fund.class);
    }

}
