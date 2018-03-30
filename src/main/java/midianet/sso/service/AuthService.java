package midianet.sso.service;

import midianet.sso.JwtUtil;
import midianet.sso.domain.Auth;
import midianet.sso.domain.Person;
import midianet.sso.domain.UserAgent;
import midianet.sso.repository.AuthRepository;
import midianet.sso.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private PersonRepository userRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional(rollbackFor = Exception.class)
    public Auth auth(String username, String password, UserAgent agent){
        Assert.notNull(username, "Usuário não informado");
        Assert.notNull(username, "Senha não informada");
        Person person = userRepository.findUserByUsername(username).orElseThrow(() -> new EntityNotFoundException(String.format("Usuário e ou senha inválidos")));
        Assert.isTrue(password.equals(person.getPassword()),"Usuário e ou senha inválidos");
        String token = jwtUtil.generateToken(person, agent);
        Auth auth = Auth.builder().token(token).person(person).date(LocalDateTime.now()).build();
        return authRepository.save(auth);
    }

    @Transactional(rollbackFor = Exception.class)
    public void logout(String token){
        authRepository.findByToken(token).ifPresent(auth -> {
            authRepository.delete(auth);
        });
    }

}
