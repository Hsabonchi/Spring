package chat.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;


@Entity
@Setter
@Getter
public class Room implements Serializable {


    private static final long serialVersionUID = 5213811586656282160L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "room_name", unique = true)
    private String roomName;

    private boolean deleted = Boolean.FALSE;

    /*
     * tagName like social, sport .. etc
     */
    @Column(name = "tag_name")
    private String tagName;

    @Column(name = "is_private")
    private String isPrivate;

    @CreationTimestamp
    private Date date_created;

    /*
     * MediaFile is any type of file like JPG,pdf,txt.. etc Each room should have a picture mappedBy
     * in M-M relationship is always bi-directional on DB.
     *
     * We have to use mappedBy, otherwise it will be two different entities Room is the reverse owner
     * of the relationship since it has the mappedBy - mappedBy refers to the inverse side of a
     * bidirectional relationship - mappedBy = "rooms" rooms is the owner of the relationship
     */

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<MediaFile> files = new HashSet();

    /*
     * M-m Relationship between Room and Member One room can have many members Many members belong to
     * one room; Owner of the relationship can specify the name of the foreign column with
     *
     * @JoinColumn annotation.
     */

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "rooms_members")
    private Set<Member> members = new HashSet();

}


