package com.loctek.intelligent_bed_monitoring_platform.system_common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /*
     * 解决跨域问题
     *
     * registry跨域对象
     * 访问路径,请求来源，方法，最大时间，允许携带
     * */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
