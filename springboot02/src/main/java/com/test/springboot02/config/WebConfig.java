package com.test.springboot02.config;

import com.test.springboot02.interceptor.NotificationInterceptor;
import com.test.springboot02.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.StaticResourceLocation;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
//@EnableWebMvc 스프링부트가 제공하는 webMvc자동설정 사용 x
public class WebConfig implements WebMvcConfigurer {

    private final NotificationInterceptor notificationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //static 요청에는 적용하지 않는다
        List<String> staticResourcesPath = Arrays.stream(StaticResourceLocation.values())
                .flatMap(StaticResourceLocation::getPatterns)
                .collect(Collectors.toList());
        staticResourcesPath.add("/node_modules/**");

        // PathRequest.toStaticResources().atCommonLocations())  -> StaticResourceLocation.class

        registry.addInterceptor(notificationInterceptor)
        .excludePathPatterns(staticResourcesPath);

    }
}
