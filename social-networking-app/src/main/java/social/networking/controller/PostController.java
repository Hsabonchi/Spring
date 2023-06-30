package social.networking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import social.networking.dto.PostDto;
import social.networking.entity.Post;
import social.networking.repository.PostRepository;
import social.networking.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")

public class PostController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    PostService  postService;

    @GetMapping
    public List<PostDto> getPosts() {
        return postService.getAllPost();
    }

    @PostMapping
    public boolean addPost(@RequestBody Post post){

        postRepository.save(post);
        if ( post.getParentId() != null){
            Post parentPost = postRepository.findById(post.getParentId()).get();
            parentPost.getChild().add(post.getId());
            postRepository.save(parentPost);
        }
        return true;
    }


}
