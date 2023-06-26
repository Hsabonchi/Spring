package chat.dto;

import java.util.Set;
import lombok.Data;

@Data
public class RoomDto {

  private Long roomId;
  private String roomName;
  private String tagName;
  // private Set<Long> attachtId;
  private Set<Long> memberId;


}
