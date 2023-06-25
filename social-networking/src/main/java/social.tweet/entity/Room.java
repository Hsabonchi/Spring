package social.tweet.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Room implements Serializable {

    @Id
    private Long id;

    private String roomName;

    private boolean deleted = Boolean.FALSE;

    /*
     * tagName like social, sport .. etc
     */

    private Set<String> tagName;

    private String isPrivate;

    private Date date_created;

    /*
     * MediaFile is any type of file like JPG,pdf,txt.. etc Each room should have a picture mappedBy
     * in M-M relationship is always bi-directional on DB.
     *
     * We have to use mappedBy, otherwise it will be two different entities Room is the reverse owner
     * of the relationship since it has the mappedBy - mappedBy refers to the inverse side of a
     * bidirectional relationship - mappedBy = "rooms" rooms is the owner of the relationship
     */

    private Set<MediaFile> files = new HashSet();

    /*
     * M-m Relationship between Room and Member One room can have many members Many members belong to
     * one room; Owner of the relationship can specify the name of the foreign column with
     *
     * @JoinColumn annotation.
     */

    private Set<demomongodb.entity.Member> members = new HashSet();

}
