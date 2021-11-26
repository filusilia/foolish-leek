package com.ilia.leek.common.exception;

import com.ilia.leek.common.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Alice on 2021/4/16
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Component
public class ExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(@Nullable HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex) {
        log.error("system error now ,error message:" + ex.getMessage());
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            PrintWriter writer = response.getWriter();
            writer.write(ResultCode.SERVER_ERROR.getMessage());
            writer.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
