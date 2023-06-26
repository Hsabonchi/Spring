package chat.dto;

import lombok.Data;


@Data
public class PostDto {

  private Long postId;

  private String content;

  private int noLikes;

  private Long authorId;

  private Long parentId;

  // What is it a good practice
  private Long fileId;

}
