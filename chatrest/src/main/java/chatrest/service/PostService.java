package chatrest.service;


import java.util.ArrayList;
import java.util.List;

import chatrest.entity.Member;
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

      if (post.getAuthor() != null) {

        postDto.setAuthorId(post.getAuthor().getId());
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

    if (postDto.getAuthorId() != null) {
      System.out.println("herere  getMemberId ----> " + postDto.getParentId());
      post.setAuthor(memberRepository.findById(postDto.getAuthorId()).get());
    }

    post = postRepository.save(post);
    return post;
  }


  public PostDto getPostById(Long id) {

    Post post = postRepository.findById(id).get();
    PostDto postDto = new PostDto();
    postDto.setMessage(post.getContent());
    postDto.setNoLikes(post.getNoLikes());

    return postDto;
  }

  public boolean deletePostById(Long id, String username) {
    if (postRepository.existsById(id)) {
      Member member = memberRepository.getByName(username);
      Post post = postRepository.findById(id).get();
      if (post.getAuthor().getId() == member.getId()) {
        postRepository.deleteById(id);
        return true;
      } else {
        return false;
      }
    }
    return false;
  }
}