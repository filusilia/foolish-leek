package com.ilia.leek.controller;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ilia.leek.common.enums.ResultCode;
import com.ilia.leek.common.result.ResultResponse;
import com.ilia.leek.entity.Fund;
import com.ilia.leek.entity.FundCompany;
import com.ilia.leek.entity.User;
import com.ilia.leek.service.FundCompanyService;
import com.ilia.leek.service.FundService;
import com.ilia.leek.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alice on 2021/4/16
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {


    private final UserService userService;

    private final FundService fundService;
    private final FundCompanyService fundCompanyService;

    public UserController(UserService userService, FundService fundService, FundCompanyService fundCompanyService) {
        this.userService = userService;
        this.fundService = fundService;
        this.fundCompanyService = fundCompanyService;
    }

    /**
     * 全平台登录(支持code/用户名/手机号登录
     *
     * @param param loginKey登录key
     *              password 密码
     * @return JSONObject
     */
    @RequestMapping("login")
    public ResultResponse<Object> doLogin(@RequestBody JSONObject param) {
        String loginKey = param.getStr("loginKey");
        String password = param.getStr("password");
        if (ObjectUtil.hasEmpty(loginKey, password)) {
            return ResultResponse.failed(ResultCode.PARAMETER_NULL);
        }
        return userService.doLogin(loginKey, password);
    }

    /**
     * 更新自己
     * (目前允许更改性别,昵称,头像)
     *
     * @param user 用户
     * @return {@link ResultResponse<Object>}
     */
    @RequestMapping("updateMyself")
    public ResultResponse<Object> updateMyself(User user) {
        if (ObjectUtil.isEmpty(user)) {
            return ResultResponse.failed(ResultCode.PARAMETER_NULL);
        }
        return userService.updateMyself(user);
    }

    /**
     * 刷新token
     *
     * @return {@link ResultResponse<Object>}
     */
    @RequestMapping("updateLogin")
    public ResultResponse<Object> updateLogin() {
        try {
            StpUtil.checkActivityTimeout();
            StpUtil.updateLastActivityToNow();
        } catch (NotLoginException e) {
            if (NotLoginException.TOKEN_TIMEOUT.equals(e.getType())) {
                log.error("登录已经超时,不允许续期.");
            }
            return ResultResponse.failed();
        }
        return ResultResponse.success(StpUtil.getTokenInfo());
    }


    /**
     * 验证是否登录
     *
     * @return {@link ResultResponse<Boolean>}
     */
    @RequestMapping("isLogin")
    public ResultResponse<Boolean> isLogin() {
        return ResultResponse.success(StpUtil.isLogin());
    }


    /**
     * 注销
     *
     * @return {@link ResultResponse<Boolean>}
     */
    @RequestMapping("logout")
    public ResultResponse<Boolean> logout() {
        StpUtil.logout();
        return ResultResponse.success();
    }

    /**
     * 写入所有基金数据到基金表
     * 一次写入 受益终身
     * 目前数量为12436
     */
    @Deprecated
    public ResultResponse<Object> pullAll() {
        FileReader fileReader = new FileReader("fund_list.json");
        String result = fileReader.readString();
        JSONArray array = JSONUtil.parseArray(result);
        List<Fund> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JSONArray jsonArray = array.getJSONArray(i);
            Fund fund = new Fund();
            fund.setFundCode(jsonArray.getStr(0));
            fund.setShortName(jsonArray.getStr(1));
            fund.setFundName(jsonArray.getStr(2));
            fund.setFundType(jsonArray.getStr(3));
            fund.setFundFullPinyin(jsonArray.getStr(4));
            list.add(fund);
        }
//        fundService.saveBatch(list);
        List<Fund> funds = fundService.list();
        List<Fund> n = list.stream().filter(item -> !funds.contains(item)).collect(Collectors.toList());
        for (Fund fund : n) {
            fundService.save(fund);
        }
        return ResultResponse.success();
    }

    /**
     * 写入所有基金公司数据到基金公司表
     * 一次写入 受益终身
     */
    @Deprecated
    public ResultResponse<Object> pullAllCompany() {
        FileReader fileReader = new FileReader("company.json");
        String result = fileReader.readString();
        JSONArray array = JSONUtil.parseArray(result);
        List<FundCompany> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JSONArray jsonArray = array.getJSONArray(i);
            FundCompany company = new FundCompany();
            company.setCompanyCode(jsonArray.getStr(0));
            company.setCompanyName(jsonArray.getStr(1));
            list.add(company);
        }
//        fundCompanyService.saveBatch(list);
        List<FundCompany> companies = fundCompanyService.list();
        List<FundCompany> n = list.stream().filter(item -> !companies.contains(item)).collect(Collectors.toList());
        for (FundCompany company : n) {
            fundCompanyService.save(company);
        }
        return ResultResponse.success();
    }
}
