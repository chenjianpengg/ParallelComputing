package com.cjp.db.config;

import com.cjp.db.interceptors.AuthorityManageInterceptor;
import com.cjp.db.interceptors.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chenjianpeng
 */
@Configuration
@Slf4j
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(stringRedisTemplate))
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/company/user/register",
                        "/admin/user/register",
                        "/developer/user/register",
                        "/company/user/login",
                        "/admin/user/login",
                        "/developer/user/login");

        registry.addInterceptor(new AuthorityManageInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/company/user/register",
                        "/admin/user/register",
                        "/developer/user/register",
                        "/company/user/login",
                        "/admin/user/login",
                        "/developer/user/login");
    }
}

