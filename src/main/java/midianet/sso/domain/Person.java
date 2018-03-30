package midianet.sso.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    
    @NotNull
    @NotEmpty
    @Column(nullable = false,length = 80)
    private String name;
    
    @NotNull
    @NotEmpty
    @Column(nullable = false,length = 60)
    private String email;
    
    @CPF
    @NotNull
    @NotEmpty
    @Column(nullable = false,length = 11)
    private String cpf;

    @NotNull
    @NotEmpty
    @Column(nullable = false,length = 20)
    private String username;
    
    @NotNull
    @NotEmpty
    @Column(nullable = false,length = 20)
    private String password;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="grants", joinColumns=@JoinColumn(name="role_id"), inverseJoinColumns=@JoinColumn(name="person_id"))
    private List<Role> roles;
    
}