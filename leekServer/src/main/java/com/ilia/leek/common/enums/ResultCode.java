package com.ilia.leek.common.enums;

/**
 * @author Alice on 2021/4/16
 * @version 1.0
 * @since 1.0
 * <p>
 * rest请求返回枚举类
 * 1x：服务类异常
 * 2x：用户类异常
 * 3x:操作类异常
 * 4x:网络类异常
 * </p>
 */
public enum ResultCode {
    /**
     * 操作成功
     */
    SUCCESS("0000", "操作成功!"),
    /**
     * 操作失败
     */
    FAILED("1001", "操作失败!"),
    /**
     * 操作失败
     */
    SYSTEM_LOCKED("1002", "系统锁定!"),
    /**
     * 验证失败
     */
    OAUTH_FAILED("1003", "客户端验证失败，请登录!"),
    /**
     * 权限不足
     */
    PERMISSION_DENIED("1004", "客户端验证失败，权限不足!"),
    /**
     * 操作失败
     */
    SERVER_ERROR("1005", "服务器异常!"),

    /**
     * 请求方法不存在
     */
    NOT_FOUND("1006", "请求方法不存在"),
    /**
     * 请求方法不允许
     */
    METHOD_NOT_SUPPORT("1007", "请求方法不允许"),
    /**
     * 请求出错
     */
    BAD_REQUEST("1008", "请求出错。"),
    /**
     * 参数为空
     */
    PARAMETER_NULL("1009", "必填参数为空！"),
    /**
     * 参数出错
     */
    PARAMETER_ERROR("1010", "参数不合法！"),

    /**
     * 登录失败
     */
    USER_LOGIN_FAILED("2001", "登录失败，用户密码错误或用户不存在！"),

    /**
     * 登录失败,已注销
     */
    USER_LOGIN_CANCEL("2002", "登录失败，用户已注销！"),

    /**
     * 用户已存在
     */
    USER_ALREADY_EXISTS("2003", "用户已经存在！"),

    /**
     * 验证失败
     */
    OAUTH_ERROR("2004", "客户端验证失败，用户名或密码错误！"),

    /**
     * 用户未登录
     */
    USER_NO_LOGIN("2005", "未登录！"),

    /**
     * 查询,操作结果为空
     */
    SEARCH_NULL("3001", "操作结果为空！"),

    /**
     * 处理部分操作失败
     */
    MAKE_FALSE("3002", "处理部分操作失败！"),

    /**
     * 请求远程网络异常
     */
    NET_ERROR("4001", "远程请求失败！"),

    /**
     * 非法请求
     */
    DANGER_REQUEST("9999", "危险请求，请勿非法操作！");

    private final String code;

    private final String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
