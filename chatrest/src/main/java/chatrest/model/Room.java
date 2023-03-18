package chatrest.model;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;



@Entity

public class Room implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private int id;

  private String roomName;

  private int totalMember;

  private boolean deleted = Boolean.FALSE;

  @ManyToMany(mappedBy = "rooms")
  private List<Member> member;


  public Room() {
    super();
  }

  public Room(String name) {
    super();
    this.roomName = name;
    this.totalMember = 0;
  }

  // public String getRoomName() {
  // return roomName;
  // }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  // public int getTotalMember() {
  // return totalMember;
  // }

  public void setTotalMember(int totalMember) {
    this.totalMember = totalMember;
  }

  public boolean isDeleted() {
    return deleted;
  }


  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }



}
