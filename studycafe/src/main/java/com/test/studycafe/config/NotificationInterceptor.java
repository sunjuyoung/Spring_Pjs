package com.test.studycafe.config;

import com.test.studycafe.domain.Account;
import com.test.studycafe.repository.NotificationRepository;
import com.test.studycafe.security.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class NotificationInterceptor  implements HandlerInterceptor {

    private final NotificationRepository notificationRepository;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        if(modelAndView != null && isRedirectView(modelAndView) && authentication != null &&
                                        authentication.getPrincipal() instanceof UserAccount){
            Account account = ((UserAccount)authentication.getPrincipal()).getAccount();
            long count = notificationRepository.countByAccountAndChecked(account,false);
            System.out.println("-------------------------------");
            System.out.println(count);
            modelAndView.addObject("hasNotification",count>0);
        }

    }
    //핸들러 인터셉터 적용 범위 리다이렉트 요청에는 적용하지 않기
    private boolean isRedirectView(ModelAndView modelAndView) {
        return modelAndView.getViewName().startsWith("redirect:");
    }
}
