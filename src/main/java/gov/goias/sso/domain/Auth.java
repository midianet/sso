package gov.goias.sso.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long          id;
    
    @NotNull
    @NotEmpty
    @Column(nullable = false,length = 1000)
    private String        token;
    
    @NotNull
    private LocalDateTime date;
    
    @NotNull
    @ManyToOne
    private Person person;
    
}