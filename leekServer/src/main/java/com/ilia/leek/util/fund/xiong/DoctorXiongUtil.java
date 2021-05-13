package com.ilia.leek.util.fund.xiong;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ilia.leek.entity.Fund;

/**
 * @author Alice on 2021/5/12
 * @version 1.0
 * @since 1.0
 */
public class DoctorXiongUtil {
    /**
     * 解析小熊的数据
     *
     * @param data DoctorXiongResponse.data
     * @return Fund
     */
    public static Fund createFundByDoctorXiong(String data) {
        if (ObjectUtil.isEmpty(data)) {
            return null;
        }
        JSONObject jsonObject = JSONUtil.parseObj(data);
        Fund fund = JSONUtil.toBean(jsonObject, Fund.class);
        fund.setFundCode(jsonObject.getStr("code"));
        return fund;
    }
}
