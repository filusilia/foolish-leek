package com.ilia.leek.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author alice on 2019/12/18 0028.
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class Constants {

    /**
     * 项目工作模式
     */
    public static String MODE = "";

    /**
     * 项目名称
     */
    public static String PROJECT = "";
    /**
     * 项目版本
     */
    public static String VERSION = "";

    /**
     * 锁定系统字段,默认0,未锁定
     */
    public static int SYSTEM_LOCKED = 0;

    /**
     * 项目中心ip(系统所在主机ip),,系统默认为可以查看配置文件 servlet.context-path
     */
    public static String PROJECT_IP = "127.0.0.1";
    /**
     * 项目指定tomcat 端口号,系统默认为可以查看配置文件server.port
     */
    public static String PROJECT_PORT = "";

    @Value("${spring.profiles.active}")
    public void setMode(String mode) {
        MODE = mode;
    }

    @Value("${server.port}")
    public void setProjectPort(String centralPort) {
        PROJECT_PORT = centralPort;
    }

    @Value("${info.app.name}")
    private void setPROJECT(String PROJECT) {
        Constants.PROJECT = PROJECT.trim().replace("/", "");
    }

    @Value("${info.app.version}")
    private void setVER(String VER) {
        Constants.VERSION = VER;
    }
}
