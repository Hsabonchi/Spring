package chatrest.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Room implements Serializable {


  private static final long serialVersionUID = 5213811586656282160L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "total_number")
  private int totalMember = 0;

  @Column(name = "room_name")
  private String roomName;

  private boolean deleted = Boolean.FALSE;

  @Column(name = "tag_name", columnDefinition = "VARCHAR(75)")
  private String tagName = "";

  // (attachment is any type of file like JPG,pdf,txt)
  // media_files
  @ManyToMany(mappedBy = "room")
  private Set<Attachment> attachment = new HashSet();

  @CreationTimestamp
  private Date date_created;

  // How to create created by attributes ??

  @ManyToMany(mappedBy = "rooms")
  private Set<Member> member = new HashSet();

}
