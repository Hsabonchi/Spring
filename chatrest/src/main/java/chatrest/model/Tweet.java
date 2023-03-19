package chatrest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tweet implements Serializable {

  private static final long serialVersionUID = -3061126648401162029L;

  @Id
  @GeneratedValue
  private int id;

  @Column(length = 40000)
  private String text;

  @Column(columnDefinition = "integer default 0")
  private int noLikes;

  @CreationTimestamp
  private Date date_created;

  @OneToMany
  private List<Attachment> attachment;

}
