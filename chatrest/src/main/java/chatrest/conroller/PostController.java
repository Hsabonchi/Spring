package chatrest.conroller;

import java.util.List;

import chatrest.mapper.PostMapper;
import chatrest.utils.JwtTokenUtil;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import chatrest.dto.PostDto;
import chatrest.entity.Post;
import chatrest.service.PostService;
import lombok.RequiredArgsConstructor;

//@Api(tags = "Post")
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;
  private final JwtTokenUtil  jwtTokenUtil;
  private final PostMapper postMapper;


  @GetMapping
  public List<PostDto> getPosts() {
    return postService.getAllPost();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Post add(@RequestBody PostDto postDto) {
    System.out.println("in post Controller =======================================");
    return postService.add(postDto);
  }

  @GetMapping("/{id}")
  public PostDto getPostsById(@PathVariable Long id) {
    return postService.getPostById(id);
  }

  @DeleteMapping()
  public boolean delete(@RequestParam Long id, @RequestHeader("Authorization") String token) {
      // get the token from char at index to the end
      token =  token.substring(7);
    String  userName = jwtTokenUtil.getUsernameFromToken(token);

    return postService.deletePostById(id,userName);
  }

  // Edit post content and or image file



}
