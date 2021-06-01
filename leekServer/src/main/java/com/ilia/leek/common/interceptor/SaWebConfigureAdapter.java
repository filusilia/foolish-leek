package com.ilia.leek.common.interceptor;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouterUtil;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.models.HttpMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

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
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册路由拦截器，自定义验证规则
        registry.addInterceptor(new SaRouteInterceptor((request, response, handler) -> {
            // 登录验证 -- 排除多个路径
            SaRouterUtil.match(Collections.singletonList("/**"), Arrays.asList("/user/login","/user/putAll", "/user/isLogin"), StpUtil::checkLogin);
            // 角色认证 -- 拦截以 admin 开头的路由，必须具备[admin]角色或者[super-admin]角色才可以通过认证
            SaRouterUtil.match("/admin/**", () -> StpUtil.checkRoleOr("admin", "super-admin"));
            // 权限认证 -- 不同模块, 校验不同权限
//            SaRouterUtil.match("/fund/**", () -> StpUtil.checkPermission("admin"));
            // 匹配 restful 风格路由
            SaRouterUtil.match("/fund/get/{id}", () -> StpUtil.checkPermission("user"));

            // 检查请求方式
            SaRouterUtil.match("/notice/**", () -> {
                if (request.getMethod().equals(HttpMethod.GET.toString())) {
                    StpUtil.checkPermission("notice");
                }
            });

            // 在多账号模式下，可以使用任意StpUtil进行校验
//            SaRouterUtil.match("/user/**", StpUtil::checkLogin);

        })).addPathPatterns("/**");
    }
}
