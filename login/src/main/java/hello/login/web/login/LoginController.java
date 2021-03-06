package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.http.HttpResponse;

@Slf4j
@Controller
@RequiredArgsConstructor

public class LoginController {

    private final LoginService loginService;
   // private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form){
        return "login/loginForm";
    }




    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult,
                        HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member member = loginService.login(loginForm.getLoginId(),loginForm.getPassword());
        if(member == null){
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,member);


        return "redirect:/";
    }

/*    @PostMapping("/login")
    public String loginv2(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult,
                        HttpServletResponse response){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member member = loginService.login(loginForm.getLoginId(),loginForm.getPassword());
        if(member == null){
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        sessionManager.createSession(member,response);


        return "redirect:/";
    }*/
   /* @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult,
                        HttpServletResponse response){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member member = loginService.login(loginForm.getLoginId(),loginForm.getPassword());
        if(member == null){
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }
        //시간 정보를 주지 않으면 세션쿠키(브라우저 종료시 같이 만료)
        Cookie memberId = new Cookie("memberId", String.valueOf(member.getId()));
        response.addCookie(memberId);

        return "redirect:/";
    }*/

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(session !=null){
            session.invalidate();
        }

        return "redirect:/";
    }

}
