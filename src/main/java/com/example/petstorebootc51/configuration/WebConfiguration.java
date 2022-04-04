package com.example.petstorebootc51.configuration;

import com.example.petstorebootc51.interceptor.ApiInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private final ApiInterceptor apiInterceptor;

    public WebConfiguration(ApiInterceptor apiInterceptor) {
        this.apiInterceptor = apiInterceptor;
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user")
                .excludePathPatterns("/swagger-ui/**")
                .excludePathPatterns("/v2/**")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/webjars/**");
    }
}
