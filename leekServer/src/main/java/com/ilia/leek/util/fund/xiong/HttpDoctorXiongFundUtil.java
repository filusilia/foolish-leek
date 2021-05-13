package com.ilia.leek.util.fund.xiong;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.ilia.leek.common.enums.ResultCode;
import com.ilia.leek.common.exception.BaseBusinessException;
import com.ilia.leek.util.fund.FundConstant;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Alice on 2021/4/26
 * @version 1.0
 * @since 1.0
 */
@Slf4j
public class HttpDoctorXiongFundUtil {

    public static final String DOCTORXIONG_SUCCESS = "200";

    /**
     * 查询小熊的基金详情
     * e:https://api.doctorxiong.club/v1/fund/detail?code=000001&startDate=2020-09-01
     *
     * @return {@link DoctorXiongResponse}
     */
    public static DoctorXiongResponse getDetail(String code) {
        log.info("http get from DoctorXiong,fund code:{}", code);
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String result = HttpUtil.get(FundConstant.DOCTORXIONG_DETAIL + "?code=" + code + "&startDate=" + date);
        log.info("getDetail response:{}", result);
        if (!ObjectUtil.isEmpty(result)) {
            return JSONUtil.toBean(result, DoctorXiongResponse.class);
        }
        throw new BaseBusinessException(ResultCode.NET_ERROR);
    }

    /**
     * 查询小熊的基金详情
     * e:https://api.doctorxiong.club/v1/fund/detail?code=000001&startDate=2020-09-01
     *
     * @return {@link DoctorXiongResponse}
     */
    public static DoctorXiongResponse getFund(String codes) {
        log.info("http get from DoctorXiong,fund list:{}", codes);
        String result = HttpUtil.get(FundConstant.DOCTORXIONG_FUND + "?code=" + codes);
        log.info("getFund response:{}", result);
        if (!ObjectUtil.isEmpty(result)) {
            return JSONUtil.toBean(result, DoctorXiongResponse.class);
        }
        throw new BaseBusinessException(ResultCode.NET_ERROR);
    }
}
