package com.ilia.leek.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ilia.leek.common.enums.LoginType;
import com.ilia.leek.common.enums.ResultCode;
import com.ilia.leek.common.result.ResultResponse;
import com.ilia.leek.entity.User;
import com.ilia.leek.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author Alice on 2021/4/16
 * @version 1.0
 * @since 1.0
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    public ResultResponse<Object> doLogin(String loginKey, String password) {
        LoginType type = LoginType.USERNAME;
        if (NumberUtil.isNumber(loginKey)) {
            //超过8位就当做它是手机号好了
            if (loginKey.length() == 8) {
                type = LoginType.MOBILE;
            } else {
                type = LoginType.USER_CODE;
            }
        }
        User user = null;
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        switch (type) {
            case USER_CODE:
                wrapper.eq(User::getUserCode, Long.valueOf(loginKey));
                user = getOne(wrapper);
                break;
            case USERNAME:
                wrapper.eq(User::getUsername, loginKey);
                user = getOne(wrapper);
                break;
            case MOBILE:
                wrapper.eq(User::getMobile, Long.valueOf(loginKey));
                user = getOne(wrapper);
                break;
            default:
                log.debug("啥,怎么会到这里的");
        }
        if (null == user) {
            return ResultResponse.failed(ResultCode.USER_LOGIN_FAILED);
        }
        //密码判断(在做了
        if (!user.getPassword().equals(password)) {
            return ResultResponse.failed(ResultCode.USER_LOGIN_FAILED);
        }
        if (1 == user.getStatus()) {
            return ResultResponse.failed(ResultCode.USER_LOGIN_CANCEL);
        }
        //登录成功
        StpUtil.setLoginId(user.getId());
        JSONObject jsonObject = new JSONObject();
        //清空敏感数据
        user.setId(null);
        user.setCreateTime(null);
        user.setPassword(null);
        user.setUpdateTime(null);
        user.setStatus(null);
        jsonObject.set("myInfo", user);
        //权限列表(在做了在做了
        jsonObject.set("per_list", "");
        jsonObject.set("token", StpUtil.getTokenInfo());
        return ResultResponse.success(jsonObject);
    }
}


