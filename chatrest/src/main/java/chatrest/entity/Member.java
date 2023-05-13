package chatrest.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Member implements Serializable {

  private static final long serialVersionUID = 1248353164264761487L;

  @Id
  @GeneratedValue
  private Integer id;

  @Column(name = "user_name", nullable = false, unique = true)
  private String userName;

  private String password;

  @Column(nullable = false, unique = true)
  private String primaryEmail;

  @Column(name = "is_admin")
  private boolean isAdmin = false;

  @OneToOne
  private Attachment attachment;


  @ManyToMany
  @JoinTable(name = "room_member", joinColumns = {@JoinColumn(name = "m_id")},
      inverseJoinColumns = {@JoinColumn(name = "r_id")})
  private Set<Room> rooms = new HashSet();

}
