package com.ilia.leek.util.fund;

import com.ilia.leek.entity.Fund;

import java.util.List;

/**
 * @author Alice on 2021/4/26
 * @version 1.0
 * @since 1.0
 * 基金请求控制器,用于封装基金请求地址,应对假如请求地址发生改变的情况
 */
public interface FundAndCompanyRequestInterface {
    /**
     * 获取全部基金
     *
     * @return List<Fund>
     */
    List<Fund> getAllFund();

    /**
     * 根据基金代码实时获取基金详情
     *
     * @param code 基金代码
     * @return Fund
     */
    Fund getRealTimeFundByCode(String code);

    /**
     * 根据基金code列表查询基金的基础信息
     *
     * @param codes 基金代码列表
     * @return List<Fund>
     */
    List<Fund> getDetailByCodes(List<String> codes);

    /**
     * 基金排行榜
     *
     * @return List<Fund>
     */
    List<Fund> getRankFund();
}
