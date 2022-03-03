package com.ilia.leek.common.exception;

import com.ilia.leek.common.enums.ResultCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Alice on 2021/4/16
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
public class BaseBusinessException extends RuntimeException {

    private static final long serialVersionUID = -7638191744231804818L;
    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;


    public BaseBusinessException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public BaseBusinessException(ResultCode resultCode) {
        super();
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }
}
