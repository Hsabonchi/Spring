package chatrest.model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Room implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private int id;

  private String nameX;

  private int totalMember;



  public Room() {
    super();
    // TODO Auto-generated constructor stub
  }


  public Room(String name) {
    super();
    this.nameX = name;
    this.totalMember = 0;
  }


  public String getName() {
    return nameX;
  }

  public void setName(String name) {
    this.nameX = name;
  }

  public int getTotalMember() {
    return totalMember;
  }

  public void setTotalMember(int totalMember) {
    this.totalMember = totalMember;
  }



}
