package com.test.springboot02.interceptor;

import com.test.springboot02.entity.Member;
import com.test.springboot02.repository.NotificationRepository;
import com.test.springboot02.security.AuthMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationInterceptor implements HandlerInterceptor {

    private final NotificationRepository notificationRepository;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(modelAndView != null && !isRedirectView(modelAndView) && authentication != null && authentication.getPrincipal() instanceof AuthMember){
            Member member = (((AuthMember) authentication.getPrincipal()).getMember());
            long count = notificationRepository.countByMemberAndChecked(member, false);
            modelAndView.addObject("hasNotification",count>0);
            modelAndView.addObject("notificationCount",count);


        }
    }

    //리다이렉트 요청에는 적용하지 않는다.
    private boolean isRedirectView(ModelAndView modelAndView) {
        return modelAndView.getViewName().startsWith("redirect:");
    }
}
