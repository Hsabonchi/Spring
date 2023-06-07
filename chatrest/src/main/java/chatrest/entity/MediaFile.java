package chatrest.entity;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class MediaFile implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private int size;

  private String type;

  @Lob
  @Column(length=1048576)
  private byte[] content;

  @CreationTimestamp
  @Column(name = "date_created")
  private Date dateCreated;



}
