package gov.goias.sso.repository;

import gov.goias.sso.domain.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person,String> {
    Optional<Person> findUserByUsername(String username);
}