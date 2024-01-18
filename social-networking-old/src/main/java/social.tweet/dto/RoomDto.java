package social.tweet.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RoomDto {

    private Long roomId;
    private String roomName;
    private String tagName;
    private Set<Long> attachtId;
    private Set<Long> memberId;
}