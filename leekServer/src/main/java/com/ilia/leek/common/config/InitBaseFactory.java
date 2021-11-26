package com.ilia.leek.common.config;

import cn.hutool.core.util.ObjectUtil;
import com.ilia.leek.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Alice on 2021/4/16
 * @version 1.0
 * @since 1.0
 * <p>
 * 初始化工厂
 * </p>
 */
@Slf4j
@Component
public class InitBaseFactory implements CommandLineRunner {
    private final String DEV = "dev";
    private final String PROD = "prod";
    private final String TEST = "test";

    private final Environment env;

    @Autowired
    public InitBaseFactory(Environment env) {
        this.env = env;
    }

    @Override
    public void run(String... args) {
        try {
            Constants.MODE = env.getProperty("spring.profiles.active");
            if (ObjectUtil.isEmpty(Constants.MODE)) {
                throw new NullPointerException("MODE is unknown");
            }
            log.info("workspace :{}", Constants.MODE);

        } catch (Exception e) {
            log.error("create basis config error ,system exit.\nerror message:{}", e.getMessage());
            System.exit(0);
        }
    }
}
