package com.ilia.leek.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ilia.leek.common.enums.ResultCode;
import com.ilia.leek.common.result.ResultResponse;
import com.ilia.leek.entity.UserFund;
import com.ilia.leek.entity.model.BaseResultModel;
import com.ilia.leek.entity.model.ResultUserFund;
import com.ilia.leek.entity.pojo.QueryFund;
import com.ilia.leek.mapper.UserFundMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Alice on 2021/4/20
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
public class UserFundService extends ServiceImpl<UserFundMapper, UserFund> {

    /**
     * 添加我的基金
     *
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
     * 最喜欢的基金
     *
     * @param queryFund 查询基金
     * @return {@link ResultResponse<Object>}
     */
    public ResultResponse<Object> favoriteFund(QueryFund queryFund) {
        Long userId = (Long) StpUtil.getLoginId();
        LambdaQueryWrapper<UserFund> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFund::getUserId, userId);
        wrapper.eq(UserFund::getFundCode, queryFund.getFundCode());
        UserFund userFund = getOne(wrapper);
        if (ObjectUtil.isEmpty(userFund)) {
            return ResultResponse.failed(ResultCode.NO_FUND);
        }
        userFund.setFavorite(queryFund.getFavorite());
        boolean success = updateById(userFund);
        return success ? ResultResponse.success() : ResultResponse.failed();
    }

    /**
     * 分页查询我的基金
     *
     * @param queryFund
     * @return
     */
    public ResultResponse<Object> getMyFunds(QueryFund queryFund) {
        Long userId = StpUtil.getLoginIdAsLong();
        queryFund.setUserId(userId);
        Page<ResultUserFund> page = PageHelper.startPage(queryFund.getPage(), queryFund.getPageSize())
                .doSelectPage(() -> getBaseMapper().getFundsByUserId(queryFund));
        BaseResultModel<ResultUserFund> resultModel = new BaseResultModel<>();
        resultModel.setTotal(page.getTotal());
        resultModel.setData(page.getResult());
        return ResultResponse.success(resultModel);
    }
}