package chatrest.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

  @CreationTimestamp
  public Date date_created;

  /*
   * One post can have zero OR one medeiaFile One mediaFile belong to one or more posts. 1 - M
   */

  @OneToMany
  private List<MediaFile> mediaFile;

  /*
   * One post can have zero OR one parent One parent can be a parent of multiple posts.
   */

  @ManyToOne
  private Post parentPost;

}
