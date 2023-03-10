package chatrest.model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Room implements Serializable {
  @Id
  @GeneratedValue
  private int id;

  private String name;

  private int totalMember;



  public Room() {
    super();
    // TODO Auto-generated constructor stub
  }


  public Room(String name) {
    super();
    this.name = name;
    this.totalMember = 0;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getTotalMember() {
    return totalMember;
  }

  public void setTotalMember(int totalMember) {
    this.totalMember = totalMember;
  }



}
