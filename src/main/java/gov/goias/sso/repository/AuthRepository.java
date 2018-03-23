package gov.goias.sso.repository;

import gov.goias.sso.domain.Auth;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthRepository extends CrudRepository<Auth,Long> {
    Optional<Auth> findByToken(String token);
}
