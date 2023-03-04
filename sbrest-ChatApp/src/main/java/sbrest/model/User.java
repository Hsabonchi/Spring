package sbrest.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Integer id;

  @Lob
  private byte[] profilePicture;



  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;



  @Column(name = "num_of_forum_posts", nullable = false)
  private int numOfForumPosts;


  // @ManyToMany(mappedBy = "users", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  // private Set<Group> groups = new HashSet<>();


  public User() {
    super();
  }



  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getNumOfForumPosts() {
    return numOfForumPosts;
  }

  public void setNumOfForumPosts(int numOfForumPosts) {
    this.numOfForumPosts = numOfForumPosts;
  }

  public byte[] getProfilePicture() {
    return profilePicture;
  }

  public void setProfilePicture(byte[] profilePicture) {
    this.profilePicture = profilePicture;
  }



  // public Set<Group> getGroups() {
  // return groups;
  // }



  // public void addGroups(Group group) {
  // groups.add(group);
  // group.getUsers().add(this);
  //
  // }



}
