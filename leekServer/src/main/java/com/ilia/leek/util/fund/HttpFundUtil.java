package com.ilia.leek.util.fund;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.ilia.leek.common.enums.ResultCode;
import com.ilia.leek.common.exception.BaseBusinessException;
import com.ilia.leek.util.fund.xiong.DoctorXiongResponse;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Alice on 2021/4/26
 * @version 1.0
 * @since 1.0
 */
@Slf4j
public class HttpFundUtil {

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
        if (!ObjectUtil.isEmpty(result)) {
            return JSONUtil.toBean(result, DoctorXiongResponse.class);
        }
        throw new BaseBusinessException(ResultCode.NET_ERROR);
    }

    /**
     * 查询天天基金网的实时基金数据
     * e:http://fundgz.1234567.com.cn/js/001186.js?rt=1463558676006
     *
     * @return String
     */
    public static String getRealTime(String code) {
        String result = HttpUtil.get(FundConstant.FUNDGZ_REAL_TIME + "/" + code + ".js?rt=" + System.currentTimeMillis());
        if (!ObjectUtil.isEmpty(result)) {
            return result;
        }
        throw new BaseBusinessException(ResultCode.NET_ERROR);
    }
}
