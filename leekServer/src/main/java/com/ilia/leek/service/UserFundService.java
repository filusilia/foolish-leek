package com.ilia.leek.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.ilia.leek.common.result.ResultResponse;
import com.ilia.leek.entity.UserFund;
import com.ilia.leek.entity.model.ResultUserFund;
import com.ilia.leek.entity.pojo.QueryFund;
import com.ilia.leek.mapper.UserFundMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alice on 2021/4/20
 * @version 1.0
 * @since 1.0
 */
@Service
public class UserFundService extends ServiceImpl<UserFundMapper, UserFund> {

    /**
     * 添加我的基金
     * @param queryFund
     * @return
     */
    public ResultResponse<Object> addMyFund(QueryFund queryFund) {
        Long userId = (Long) StpUtil.getLoginId();
        LambdaQueryWrapper<UserFund> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFund::getUserId, userId);
        wrapper.eq(UserFund::getFundCode, queryFund.getFundCode());
        UserFund userFund = getOne(wrapper);
        if (!ObjectUtil.isEmpty(userFund)) {
            return ResultResponse.success();
        }
        userFund = new UserFund();
        userFund.setUserId(userId);
        userFund.setFundCode(queryFund.getFundCode());
        userFund.setFavorite(queryFund.getFavorite());
        userFund.setSort(queryFund.getOrder());
        boolean success = save(userFund);
        return success ? ResultResponse.success() : ResultResponse.failed();
    }

    /**
     * 分页查询我的基金
     * @param queryFund
     * @return
     */
    public ResultResponse<Object> getMyFunds(QueryFund queryFund) {
        Long userId = StpUtil.getLoginIdAsLong();
        queryFund.setUserId(userId);
        PageHelper.startPage(queryFund.getPage(), queryFund.getPageSize());
        List<ResultUserFund> fundList = getBaseMapper().getFundsByUserId(queryFund);
        return ResultResponse.success(fundList);
    }
}