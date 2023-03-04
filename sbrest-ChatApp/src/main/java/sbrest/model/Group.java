package sbrest.model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

/*
 * Users and Groups - One user can belong to many groups - one group can have many users
 * 
 */
@Entity
@Table(name = "groups")
public class Group implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Integer id;

  private String name;

  private int memeber_no;

  @Lob
  private byte[] content;
  //
  // @ManyToMany()
  // @JoinTable(name = "Users_Groups", joinColumns = {@JoinColumn(name = "user_id")},
  // inverseJoinColumns = {@JoinColumn(name = "group_id")})
  // private Set<User> users = new HashSet<>();

  public Group() {
    super();

  }

  public Group(String name) {
    super();
    this.name = name;
    this.memeber_no = 0;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getMemeber_no() {
    return memeber_no;
  }

  public void setMemeber_no(int memeber_no) {
    this.memeber_no = memeber_no;
  }

  // public Set<User> getUsers() {
  // return users;
  // }
  //
  // public void setGroups(User user) {
  // this.users.add(user);
  // }

  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }



}

