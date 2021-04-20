package com.ilia.leek.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Alice on 2021/4/20
 * @version 1.0
 * @since 1.0
 */
/**
    * 登录记录
    */
@Data
@TableName(value = "leek.ft_login_log")
public class LoginLog implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 登录时间
     */
    @TableField(value = "login_time",fill = FieldFill.INSERT)
    private LocalDateTime loginTime;

    /**
     * 登录ip
     */
    @TableField(value = "login_ip")
    private String loginIp;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_LOGIN_TIME = "login_time";

    public static final String COL_LOGIN_IP = "login_ip";
}