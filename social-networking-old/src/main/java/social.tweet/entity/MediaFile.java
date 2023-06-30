package social.tweet.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Data
public class MediaFile implements Serializable {
    @Id
    public Long id;
    private String name;
    private int size;
    private String type;
    private String location;
    private Date dateCreated;
}
