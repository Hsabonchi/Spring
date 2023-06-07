package chatrest.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Data
public class Post implements Serializable {

  private static final long serialVersionUID = -3061126648401162029L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long id;

  @Column(name = "content", length = 40000)
  public String content;

  @Column(name = "no_likes", columnDefinition = "integer DEFAULT 0 ")
  public int noLikes;

  @JsonIgnore
  @CreationTimestamp
  public Date date_created;

  /*
   * A post can be created by one memeber A memeber can create many posts 1-m
   */


  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Member author;

  /*
   * One post can have zero OR one medeiaFile One mediaFile belong to one or more posts. 1 - M
   */

   @OneToOne (fetch = FetchType.EAGER)
   private MediaFile file;



  /*
   * One post can have zero OR one parent One parent can be a parent of multiple posts.
   * 
   */

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  private Post parent;

}
