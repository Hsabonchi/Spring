package chatrest.dto;

import chatrest.entity.MediaFile;
import lombok.Data;

import java.util.Set;


@Data
public class PostDto {

  private String message;

  private int noLikes;

  private Long authorId;

  private Long parentId;

  private MediaFile file;

}
