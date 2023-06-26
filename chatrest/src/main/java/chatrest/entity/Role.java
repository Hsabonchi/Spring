package chatrest.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
     @Column(name = "id")
     @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private static final String USER_ADMIN = "USER_ADMIN";
    private static final String AUTHOR_ADMIN = "AUTHOR_ADMIN";
    private static final String BOOK_ADMIN = "BOOK_ADMIN";
    private String authority;

}