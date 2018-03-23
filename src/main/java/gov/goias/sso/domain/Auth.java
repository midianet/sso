package gov.goias.sso.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
public class Auth {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long          id;
    private String        token;
    private LocalDateTime date;

    @ManyToOne
    private Person person;

}
