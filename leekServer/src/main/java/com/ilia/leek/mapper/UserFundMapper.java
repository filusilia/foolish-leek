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
     * 
     * @param queryFund
     * @return
     */
    List<ResultUserFund> getFundsByUserId(QueryFund queryFund);
}