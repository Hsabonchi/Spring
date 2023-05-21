package chatrest.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Member implements Serializable {

  private static final long serialVersionUID = 1248353164264761487L;

  @Id
  @GeneratedValue
  public Long id;

  @Column(name = "user_name", nullable = false, unique = true)
  private String userName;

  private String password;

  @Column(nullable = false)
  private String primaryEmail;

  // a Member can be an admin of Multiple Room
  /*
   * One room has multiple admins Multiple admin can mange at max 2 rooms only
   * 
   */
  @Column(name = "is_admin")
  private boolean isAdmin = false;


  @Column(name = "is_Moderator")
  private boolean isModerator;

  /*
   * one place is mappedBy, other class should joinTable.. This side id the inverse relationship of
   * the M-M, owner is the members
   */

  @ManyToMany(mappedBy = "members", cascade = CascadeType.ALL)
  private Set<Room> rooms = new HashSet();

}
