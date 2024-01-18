package social.networking.dto;

import lombok.Data;

@Data
public class MemberDto {

    private String memberId;
    private String userName;
    private String password;
    private String primaryEmail;
    private boolean isAdmin;
    private boolean isModerator;

}