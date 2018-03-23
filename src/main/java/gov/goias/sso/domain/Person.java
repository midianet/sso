package gov.goias.sso.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String name;
    private String email;
    private String cpf;
    private String username;
    private String password;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="grants", joinColumns=@JoinColumn(name="role_id"), inverseJoinColumns=@JoinColumn(name="person_id"))
    private List<Role> roles;

}