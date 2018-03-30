package midianet.sso.resource;

import midianet.sso.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/check")
public class AuthResource {
	
	@Autowired
	private AuthRepository repository;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public String check(@RequestParam("token") String token){
		repository.findByToken(token).orElseThrow(EntityNotFoundException::new);
		return token;
	}
	
}
