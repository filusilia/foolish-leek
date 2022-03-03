package com.ilia.leek.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ilia.leek.entity.Fund;
import com.ilia.leek.entity.model.ResultUserFund;
import com.ilia.leek.entity.pojo.QueryFund;

import java.util.List;

/**
 * @author Alice on 2021/5/12
 * @version 1.0
 * @since 1.0
 */
public interface FundMapper extends BaseMapper<Fund> {

    /**
     * 根据基金代码获取基金信息(包含是否绑定
     *
     * @param code 代码
     * @return {@link ResultUserFund}
     */
    ResultUserFund getLinkFundByCode(String code);

    /**
     * 搜索基金
     *
     * @param query 查询
     * @return {@link List<ResultUserFund>}
     */
    List<ResultUserFund> searchFunds(QueryFund query);
}