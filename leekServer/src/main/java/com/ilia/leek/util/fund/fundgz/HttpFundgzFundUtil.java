package com.ilia.leek.util.fund.fundgz;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import com.ilia.leek.common.enums.ResultCode;
import com.ilia.leek.common.exception.BaseBusinessException;
import com.ilia.leek.util.fund.FundConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Alice on 2021/4/26
 * @version 1.0
 * @since 1.0
 */
@Slf4j
public class HttpFundgzFundUtil {

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
