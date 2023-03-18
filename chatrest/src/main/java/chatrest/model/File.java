package chatrest.model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class File implements Serializable {

  private static final long serialVersionUID = 8640002399326229132L;

  @Id
  @GeneratedValue
  private int id;

  private String type;

  private byte[] contents;



}
