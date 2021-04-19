package com.test.studycafe.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.StaticResourceLocation;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class WebConfig  implements WebMvcConfigurer {

    private final NotificationInterceptor notificationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        List<String> staticResourcesPath=  Arrays.stream(StaticResourceLocation.values()).flatMap(staticResourceLocation -> staticResourceLocation.getPatterns()).collect(Collectors.toList());

        //StaticResourceLocation.values();// enum CSS("/css/**"), FAVICON("/**/favicon.ico") JAVA_SCRIPT("/js/**"), IMAGES("/images/**"),WEB_JARS("/webjars/**"),
        //PathRequest.toStaticResources().atCommonLocations();

        //핸들러 인터셉터 적용 범위 static리소스 요청에는 적용하지 않기
        registry.addInterceptor(notificationInterceptor)
        .excludePathPatterns(staticResourcesPath);

    }
}
