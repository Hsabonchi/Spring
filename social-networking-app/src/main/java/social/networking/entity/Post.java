package social.networking.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Document
@Data
public class Post  implements Serializable {


    @Id
    public String id;

    public String content;

    public int noLikes;

    public Date date_created;

    private String authorId;

    private String  parentId;

    private String fileId;

    private Set<String> child = new HashSet();

}
