package com.ilia.leek.common.interceptor;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Alice on 2021/4/16
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class SaWebConfigureAdapter implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    /**
     * sa框架权限配置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册路由拦截器，自定义验证规则
        registry.addInterceptor(new SaRouteInterceptor((request, response, handler) -> {
                    // 登录认证 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
                    SaRouter.match("/**", r -> StpUtil.checkLogin());

                    // 角色认证 -- 拦截以 admin 开头的路由，必须具备 admin 角色或者 super-admin 角色才可以通过认证
                    SaRouter.match("/admin/**", r -> StpUtil.checkRoleOr("admin", "super-admin"));

                    // 权限认证 -- 不同模块认证不同权限
                    SaRouter.match("/fund/get/{id}", r -> StpUtil.checkPermission("user"));
                    SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));


                })).addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/isLogin",
                        "/error",
                        //基础数据处理
                        "/user/fundFlush",
                        //Knife4j相关链接,直接放开
                        "/favicon.ico",
                        "/v3/**",
                        "/v2/**",
                        "/swagger-resources/**",
                        "/webjars/**", "/doc.html");
    }
}
