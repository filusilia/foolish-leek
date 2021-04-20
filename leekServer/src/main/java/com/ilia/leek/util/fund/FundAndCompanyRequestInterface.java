package com.ilia.leek.util.fund;

import com.ilia.leek.entity.Fund;

/**
 * @author Alice on 2021/4/26
 * @version 1.0
 * @since 1.0
 * 基金请求控制器,用于封装基金请求地址,应对假如请求地址发生改变的情况
 */
public interface FundAndCompanyRequestInterface {
    /**
     * 根据基金代码实时获取基金详情
     * @param code
     * @return
     */
    Fund getRealTimeFundByCode(String code);
    
}
