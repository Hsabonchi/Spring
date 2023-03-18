package chatrest.model;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Member implements Serializable {

  private static final long serialVersionUID = 1248353164264761487L;

  @Id
  @GeneratedValue
  private Integer id;

  private String userName;

  private byte[] profilePicture;

  @ManyToMany
  @JoinTable(name = "room_member", joinColumns = {@JoinColumn(name = "m_id")},
      inverseJoinColumns = {@JoinColumn(name = "r_id")})
  private List<Room> rooms;

  public Member() {
    super();
    // TODO Auto-generated constructor stub
  }



}
