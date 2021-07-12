package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.argumentresolver.Login;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

/*    @GetMapping("/")
    public String home() {
        return "home";

    }*/


    @GetMapping("/")
    public String homeLoginArguemntResolver(@Login Member member, Model model){

        if(member ==null){
            return "home";
        }

        model.addAttribute("member",member);
        return "loginHome";
    }



/*    @GetMapping("/")
    public String homeLogin(@SessionAttribute(name=SessionConst.LOGIN_MEMBER,required = false)Member member, Model model){

        if(member ==null){
            return "home";
        }

        model.addAttribute("member",member);
        return "loginHome";
    }*/


/*    @GetMapping("/")
    public String homeLogin(HttpServletRequest request, Model model){
        HttpSession session1 = request.getSession(false);
        if(session1 == null){
            return "home";
        }
        Member memberSession = (Member)session1.getAttribute(SessionConst.LOGIN_MEMBER);
        if(memberSession ==null){
            return "home";
        }

        model.addAttribute("member",memberSession);
        return "loginHome";
    }*/

}