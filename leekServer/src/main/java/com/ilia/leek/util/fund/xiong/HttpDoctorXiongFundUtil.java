package com.ilia.leek.util.fund.xiong;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.ilia.leek.common.enums.ResultCode;
import com.ilia.leek.common.exception.BaseBusinessException;
import com.ilia.leek.util.fund.FundConstant;
import com.ilia.leek.util.fund.RequestTool;
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
     * 查询所有基金
     *
     * @return {@link DoctorXiongResponse}
     */
    public static DoctorXiongResponse getAllFund() {
        log.info("http get from DoctorXiong,getAllFund");
        HttpResponse response = HttpUtil.createGet(FundConstant.DOCTORXIONG_ALL_FUND).addHeaders(RequestTool.HEADER).execute();
        log.info("getAllFund response:{}", response);
        if (response.getStatus() == 200) {
            return JSONUtil.toBean(response.body(), DoctorXiongResponse.class);
        } else {
            throw new BaseBusinessException(ResultCode.NET_ERROR);
        }
    }

    /**
     * 查询小熊的基金详情
     * e:https://api.doctorxiong.club/v1/fund/detail?code=000001&startDate=2020-09-01
     *
     * @return {@link DoctorXiongResponse}
     */
    public static DoctorXiongResponse getDetail(String code) {
        log.info("http get from DoctorXiong,fund code:{}", code);
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        HttpResponse response = HttpUtil.createGet(FundConstant.DOCTORXIONG_DETAIL + "?code=" + code + "&startDate=" + date)
                .addHeaders(RequestTool.HEADER).execute();
        log.info("getDetail response:{}", response);
        if (response.getStatus() == 200) {
            return JSONUtil.toBean(response.body(), DoctorXiongResponse.class);
        } else {
            throw new BaseBusinessException(ResultCode.NET_ERROR);
        }
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
        HttpResponse response = HttpUtil.createGet(FundConstant.DOCTORXIONG_FUND + "?code=" + codes)
                .addHeaders(RequestTool.HEADER).execute();
        log.info("getFund response:{}", response);
        if (response.getStatus() == 200) {
            return JSONUtil.toBean(response.body(), DoctorXiongResponse.class);
        } else {
            throw new BaseBusinessException(ResultCode.NET_ERROR);
        }
    }
}
