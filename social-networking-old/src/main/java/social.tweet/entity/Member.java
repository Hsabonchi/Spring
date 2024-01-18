package social.tweet.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;

@Data
@Document
public class Member {
    @Id
    private String id;

    private String userName;


    private String password;

    private String primaryEmail;

    private boolean isAdmin = false;

    private boolean isModerator;


}
