package com.ilia.leek.common.config;

import com.ilia.leek.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Alice on 2021/4/16
 * @version 1.0
 * @since 1.0
 * <p>
 * 初始化工厂
 * </p>
 */
@Slf4j
@Service
public class InitBaseFactory {
    private final String dev = "dev";
    private final String prod = "prod";
    private final String test = "test";

    private final Environment env;

    @Autowired
    public InitBaseFactory(Environment env) {
        this.env = env;
    }

    /**
     * 初始化基础配置
     */
    @PostConstruct
    public void init() {
        try {
            Constants.MODE = env.getProperty("spring.profiles.active");
            switch (Constants.MODE) {
                case dev:
                case test:
                case prod:
                    break;
                default:
                    log.info("system workspace unknown ,exit.");
            }
            log.info("base constant load complete.");

        } catch (Exception e) {
            log.error("create basis config error ,system exit.\nerror message:{}", e.getMessage());
            System.exit(0);
        }

    }

    /**
     * 关闭项目.
     */
    @PreDestroy
    public void des() {
        log.info("Thank you for using,destroy now.");
    }

    /**
     * 初始化dict表基础配置
     */
    void initDict() {
        log.info("init dict start.");
    }
}
