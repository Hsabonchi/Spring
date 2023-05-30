package chatrest.service;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import chatrest.dto.PostDto;
import chatrest.entity.Post;
import chatrest.repository.MemberRepository;
import chatrest.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final MemberRepository memberRepository;


  public List<PostDto> getAllPost() {
    List<PostDto> dtos = new ArrayList();

    for (Post post : postRepository.findAll()) {

      PostDto postDto = new PostDto();
      postDto.setMessage(post.getContent());
      postDto.setNoLikes(post.getNoLikes());

      if (post.getParent() != null) {
        postDto.setParentId(post.getParent().getId());
      }

      if (post.getMember() != null) {

        postDto.setMemberId(post.getMember().getId());
      }
      dtos.add(postDto);
    }
    return dtos;

  }


  public Post add(PostDto postDto) {
    System.out.println("PostDto ----> " + postDto);
    Post post = new Post();
    post.setContent(postDto.getMessage());

    if (postDto.getParentId() != null) {
      Long id = postDto.getParentId();
      Post parent = postRepository.findById(id).get();

      post.setParent(parent);
    }

    if (postDto.getMemberId() != null) {

      System.out.println("getMemberId ----> " + postDto.getMemberId());
      post.setMember(memberRepository.findById(postDto.getMemberId()).get());
    }

    post = postRepository.save(post);
    return post;



  }


  private PostDto getPostById(Long id) {

    Post post = postRepository.findById(id).get();
    PostDto postDto = new PostDto();
    postDto.setMessage(post.getContent());
    postDto.setNoLikes(post.getNoLikes());

    return postDto;


  }


}
