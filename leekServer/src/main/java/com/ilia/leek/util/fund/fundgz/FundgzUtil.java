package com.ilia.leek.util.fund.fundgz;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.ilia.leek.entity.Fund;

/**
 * @author Alice on 2021/5/12
 * @version 1.0
 * @since 1.0
 */
public class FundgzUtil {
    /**
     * 解析天天基金网的数据
     * ><太复杂了,有时间再写
     *
     * @param data data
     * @return Fund
     */
    public static Fund createFundByFundgz(String data) {
        if (ObjectUtil.isEmpty(data)) {
            return null;
        }

        return JSONUtil.toBean(data, Fund.class);
    }

}
