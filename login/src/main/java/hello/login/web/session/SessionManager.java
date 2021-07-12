package hello.login.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    private Map<String,Object> sessionStore = new ConcurrentHashMap<>();
    private static final String SESSION_COOKIE_NAME = "mySessionIKd";


    public void createSession(Object value, HttpServletResponse response){

        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId,value);

        //쿠키새성
        Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(cookie);

    }

    public Object getSession(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if(sessionCookie ==null){
            return null;
        }
        return sessionStore.get(sessionCookie.getValue());

    }

    public void expire(HttpServletRequest request){
        Cookie cookie = findCookie(request, SESSION_COOKIE_NAME);
        if(cookie !=null){
            sessionStore.remove(cookie.getValue());
        }
    }


    public Cookie findCookie(HttpServletRequest request,String cookieName){
        if(request.getCookies()==null){
            return null;
        }
        return Arrays.stream(request.getCookies())
                .filter(cookie->cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }


}
