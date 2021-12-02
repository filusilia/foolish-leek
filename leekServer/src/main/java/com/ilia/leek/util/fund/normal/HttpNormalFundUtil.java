package com.ilia.leek.util.fund.normal;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.ilia.leek.common.enums.ResultCode;
import com.ilia.leek.common.exception.BaseBusinessException;
import com.ilia.leek.entity.Fund;
import com.ilia.leek.util.fund.FundConstant;
import com.ilia.leek.util.fund.RequestTool;

import java.util.List;

/**
 * @author Alice on 2022/1/17
 * @version 1.0
 * @since 1.0
 */
public class HttpNormalFundUtil {
    public static List<Fund> getRankFunds() {
        HttpResponse response = HttpUtil.createGet(FundConstant.RANK_FUND_URL).addHeaders(RequestTool.header).execute();
        if (response.getStatus() == 200) {
            return createFunds(response.body());
        } else {
            throw new BaseBusinessException(ResultCode.NET_ERROR);
        }
    }

    private static List<Fund> createFunds(String body) {
        if (ObjectUtil.isEmpty(body)) {
            return null;
        }
        return null;
    }
}
