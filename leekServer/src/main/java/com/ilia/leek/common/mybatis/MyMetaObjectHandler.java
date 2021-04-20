package com.ilia.leek.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Alice on 2021/4/26
 * @version 1.0
 * @since 1.0
 * mybatis_plus 自动填充功能
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("start insert fill ....");
        this.strictUpdateFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("start update fill ....");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);

    }
}
