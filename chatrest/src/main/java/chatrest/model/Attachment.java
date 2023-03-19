package chatrest.model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Attachment implements Serializable {

  private static final long serialVersionUID = 5675848444199517842L;

  @Id
  @GeneratedValue
  private int id;

  private String type;

  private byte[] contents;

}
