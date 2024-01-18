package social.networking.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.HashSet;

@Data
@Document
public class Member {
    @Id
    private String id;

    @Indexed(unique = true)
    private String userName;

    private String password;

    @Indexed(unique = true)
    private String primaryEmail;

    private boolean isAdmin = false;

    private boolean isModerator;

    private boolean deleted = Boolean.FALSE;


}
