package gov.goias.sso;

import gov.goias.sso.domain.Auth;
import gov.goias.sso.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class LoginController {

    @Value("${cookieName}")
    private String jwtTokenCookieName;

    @Value("${signingKey}")
    private String signingKey;

    private static final Map<String, String> credentials = new HashMap<>();

    @Autowired
    private HttpServletRequest request;

    public LoginController() {
        credentials.put("admin", "admin");
    }

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String home(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/authenticated")
    public String authenticated() {
        return "authenticated";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request , HttpServletResponse response){
        //request.getParameterMap().forEach((s, strings) -> System.out.println(s + " - " + strings[0]));
        authService.logout(CookieUtil.getValue(request,jwtTokenCookieName));
        CookieUtil.clear(response, jwtTokenCookieName);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(HttpServletResponse httpServletResponse, String username, String password, String redirect, Model model){
        Auth auth = authService.auth(username,password);
        CookieUtil.create(httpServletResponse, jwtTokenCookieName,auth.getToken(), false, 2147483647, "10.6.156.95");
        return "redirect:" + (StringUtils.isEmpty(redirect) ? "/authenticated" : redirect);
    }

}
