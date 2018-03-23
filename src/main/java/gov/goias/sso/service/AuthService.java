package gov.goias.sso.service;

import gov.goias.sso.JwtUtil;
import gov.goias.sso.domain.Auth;
import gov.goias.sso.domain.Person;
import gov.goias.sso.repository.AuthRepository;
import gov.goias.sso.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
    public Auth auth(String username, String password){
        //Optional.of(username).orElseThrow(() -> new IllegalArgumentException("Usuário não informado"));
        //Optional.of(password).orElseThrow(() -> new IllegalArgumentException("password não informado"));
        Assert.notNull(username, "Usuário não informado");
        Assert.notNull(username, "Senha não informada");
        Person person = userRepository.findUserByUsername(username).orElse(Person.builder().build());
        Assert.isTrue(password.equals(person.getPassword()),"Usuário e ou senha inválidos");
        String token = jwtUtil.generateToken(person);
        Auth auth = Auth.builder().token(token).date(LocalDateTime.now()).build();
        return authRepository.save(auth);
    }

    @Transactional(rollbackFor = Exception.class)
    public void logout(String token){
        authRepository.findByToken(token).ifPresent(auth -> {
            authRepository.delete(auth);
            System.out.println("move token to history");
        });

    }

}
