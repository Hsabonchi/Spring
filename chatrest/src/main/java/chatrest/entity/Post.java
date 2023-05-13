package chatrest.entity;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Post implements Serializable {

  private static final long serialVersionUID = -3061126648401162029L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long id;

  @Column(name = "message", length = 40000)
  public String message;

  @Column(name = "no_likes", columnDefinition = "integer DEFAULT 0 ")
  public int noLikes;

  @CreationTimestamp
  public Date date_created;

  // @OneToMany
  // private List<Attachment> attachment;

}
