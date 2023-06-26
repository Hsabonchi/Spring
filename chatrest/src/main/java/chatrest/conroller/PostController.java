package chatrest.conroller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import chatrest.dto.PostDto;
import chatrest.entity.Post;
import chatrest.service.PostService;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;


  @GetMapping
  @ResponseStatus(HttpStatus.CREATED)
  public List<PostDto> getPosts() {
    return postService.getAllPost();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Post add(@RequestBody PostDto postDto) {
    System.out.println("in post Controller");
    return postService.add(postDto);

  }


}
