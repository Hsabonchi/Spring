package chatrest.entity;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
  private Member member;
  /*
   * One post can have zero OR one medeiaFile One mediaFile belong to one or more posts. 1 - M
   */

  // @OneToMany
  // private Set<MediaFile> mediaFile = new HashSet();;

  /*
   * One post can have zero OR one parent One parent can be a parent of multiple posts.
   * 
   */

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Post parent;

}
