package chatrest.dto;

import lombok.Data;


@Data
public class PostDto {



  private String message;

  private int noLikes;

  private Long memberId;

  private Long parentId;

  // private String parentName;

}
