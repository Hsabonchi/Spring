package chatrest.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Attachment implements Serializable {

  private static final long serialVersionUID = 5675848444199517842L;

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  private String type;

  private byte[] contents;

  @Column(name = "public", nullable = false)
  private boolean isPublic;

  @Column(nullable = false)
  private boolean deleted;

  @Column(name = "is_room_profile_picture", nullable = false)
  private boolean isRoomProfilePicture;

  @Column(name = "is_member_profile_picture", nullable = false)
  private boolean isMemberProfilePicture;

  @Column(name = "picture", nullable = false)
  private boolean isPicture;

  @ManyToMany
  private Set<Room> room = new HashSet();

}
