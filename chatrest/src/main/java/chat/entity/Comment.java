package chat.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "comment_message")
    private String commentMessage;


//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Member author;
//
//    @OneToOne(fetch = FetchType.EAGER)
//    private MediaFile file;



    // private Set<Post> comments = new HashSet();

//    @JsonIgnore
//    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//    private Post parent;

}
