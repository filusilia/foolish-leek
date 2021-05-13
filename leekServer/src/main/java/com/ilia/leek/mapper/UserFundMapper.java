package com.ilia.leek.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ilia.leek.entity.UserFund;
import com.ilia.leek.entity.model.ResultUserFund;
import com.ilia.leek.entity.pojo.QueryFund;

import java.util.List;

/**
 * @author Alice on 2021/4/26
 * @version 1.0
 * @since 1.0
 */
public interface UserFundMapper extends BaseMapper<UserFund> {
    /**
     * 根据用户查询基金列表
     * @param queryFund
     * @return
     */
    List<ResultUserFund> getFundsByUserId(QueryFund queryFund);

    /**
     * 查询当前项目中所有被使用的基金code
     * @return
     */
    List<String> getFundsCodesDis();
}