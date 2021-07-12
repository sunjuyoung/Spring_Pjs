package hello.login.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LoginInterceptor  implements HandlerInterceptor {

    private static final String LOG_ID="logId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        request.setAttribute(LOG_ID,uuid);


        //@RequestMapping : handlerMethod
        //정적리소스 : ResourceHttpRequestHandler
        if(handler instanceof HandlerMethod){
            HandlerMethod hand = (HandlerMethod) handler;//호출할 컨트롤러 메서드의 모든 정보가 포함되어있다
        }

        log.info("REQUEST [{}][{}][{}]",uuid,requestURI,handler);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        log.info("POSTHANDLEER [{}]",modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Object logId = request.getAttribute(LOG_ID);
        String requestURI = request.getRequestURI();

        log.info("RESPONSE [{}] [{}] [{}]",logId,requestURI,handler);
        if(ex !=null){
            log.error("after error",ex);
        }
    }
}
