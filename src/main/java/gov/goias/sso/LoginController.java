package gov.goias.sso;

import gov.goias.sso.domain.Auth;
import gov.goias.sso.domain.UserAgent;
import gov.goias.sso.repository.AuthRepository;
import gov.goias.sso.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class LoginController {

    @Value("${cookie}")
    private String tokenName;

    @Value("${key}")
    private String key;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthRepository authRepository;

    @GetMapping("/")
    public String home(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        String token = CookieUtil.token(request, tokenName);
        if(!StringUtils.isEmpty(token)){
            if(authRepository.findByToken(token).isPresent()) {
                return "redirect:/authenticated";
            }
        }
        return "login";
    }

    @GetMapping("/authenticated")
    public String authenticated() {
        return "authenticated";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request , HttpServletResponse response){
        authService.logout(CookieUtil.token(request, tokenName));
        CookieUtil.delete(response, tokenName);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpServletRequest request, HttpServletResponse response, String redirect, Model model){
        try {
            UserAgent agent = new UserAgentUtil().create(request);
            Auth auth = authService.auth(username, password, agent);
            CookieUtil.create(response, tokenName, auth.getToken(), false, 2147483647, "localhost");
            return "redirect:" + (StringUtils.isEmpty(redirect) ? "/authenticated" : redirect);
        }catch (IllegalArgumentException e){
            model.addAttribute("error", "Usuário e ou senha inválidos!");
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
        }
        return "login";
    }

}
