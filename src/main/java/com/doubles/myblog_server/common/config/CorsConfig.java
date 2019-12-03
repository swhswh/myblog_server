package com.doubles.myblog_server.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * CorsConfig
 * 跨域设置类
 * @author swh
 * @date 2019-10-29
 */

@Configuration
public class CorsConfig extends WebMvcConfigurationSupport {
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("----------------------");
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8081","http://localhost:5000")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }
}
