package com.ilia.leek;

import cn.dev33.satoken.SaManager;
import com.ilia.leek.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author Alice on 2021/4/16
 * @version 1.0
 * @since 1.0
 */
@EnableOpenApi
@SpringBootApplication
@EnableConfigurationProperties
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("com.ilia.leek.mapper")
@Slf4j
public class LeekApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeekApplication.class, args);
        log.info("project name : {}", Constants.PROJECT);
        log.info("project version : {}", Constants.VERSION);
        log.info("project port : {}", Constants.PROJECT_PORT);
        log.info("powered by : ilia");
        log.info("启动成功：sa-token配置如下：{}", SaManager.getConfig());
    }
}
