package social.networking.dto;

import lombok.Data;

@Data
public class PostDto {


    private String postId;

    private String content;

    private String authorId;

    private String parentId;

    private int noLikes;

    private String fileId;

}
