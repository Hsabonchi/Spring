package chat.dto;

import lombok.Data;


@Data
public class PostDto {

  private Long postId;

  private String content;


  private Long authorId;

  private Long parentId;

  private int noLikes;

  // What is it a good practice
  private Long fileId;

}
