package com.ilia.leek.common.exception;

import com.ilia.leek.common.enums.ResultCode;
import com.ilia.leek.common.result.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.ilia.leek.common.result.ResultResponse.*;

/**
 * @author Alice on 2021/4/16
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    setValue(format.parse(text));
                } catch (ParseException e) {
                    log.error(e.getMessage());
                }
            }
        });
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultResponse<Object> noHandlerFoundException(NoHandlerFoundException ex) {
        log.error(ex.getMessage());
        return failed(ResultCode.NOT_FOUND);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultResponse<Object> constraintViolationException(ConstraintViolationException ex) {
        log.error(ex.getMessage());
        return failed(ResultCode.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResultResponse<Object> methodArgumentNotValidException(HttpRequestMethodNotSupportedException ex) {
        log.error(ex.getMessage());
        return failed(ResultCode.METHOD_NOT_SUPPORT);
    }

    @ExceptionHandler(value = {BaseBusinessException.class})
    public ResultResponse<Object> baseBusinessExceptionHandler(BaseBusinessException exception) {
        log.error(exception.getMessage());
        return init(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResultResponse<Object> defaultErrorHandler(HttpServletRequest request, Exception exception) throws Exception {
        log.error(exception.getMessage());
        return failed(ResultCode.SERVER_ERROR);
    }
}
