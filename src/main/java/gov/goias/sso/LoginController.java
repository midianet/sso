package gov.goias.sso;

import gov.goias.sso.domain.Auth;
import gov.goias.sso.domain.Person;
import gov.goias.sso.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class LoginController {

    @Value("${cookie}")
    private String token;

    @Value("${key}")
    private String key;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String home(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return StringUtils.isEmpty(CookieUtil.token(request, token)) ? "login" : "redirect:/authenticated";
    }

    @GetMapping("/authenticated")
    public String authenticated() {
        return "authenticated";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request , HttpServletResponse response){
        authService.logout(CookieUtil.token(request, token));
        CookieUtil.delete(response, token);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(HttpServletResponse httpServletResponse, String username, String password, String redirect, Model model){
        try {
            Auth auth = authService.auth(username, password);
            CookieUtil.create(httpServletResponse, token, auth.getToken(), false, 2147483647, "localhost");
            return "redirect:" + (StringUtils.isEmpty(redirect) ? "/authenticated" : redirect);
        }catch (IllegalArgumentException e){
            model.addAttribute("error", "Usuário e ou senha inválidos!");
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
        }
        return "login";
    }

}
