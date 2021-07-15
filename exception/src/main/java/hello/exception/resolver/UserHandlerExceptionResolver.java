package hello.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.exception.ex.UserException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if(ex instanceof UserException){
            log.info("UserException to 400");
            String accept = request.getHeader("accept");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            if("application/json".equals(accept)){
                Map<String,Object> result = new HashMap<>();
                result.put("ex",ex.getClass());
                result.put("message",ex.getMessage());

                String s = objectMapper.writeValueAsString(result);

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(s);

                return new ModelAndView();

            }else{
                return new ModelAndView("error/500");
            }
        }

        return null;
    }
}
