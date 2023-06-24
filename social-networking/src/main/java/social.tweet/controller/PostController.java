package demomongodb.controller;

import demomongodb.entity.Post;
import demomongodb.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")

public class PostController {

    private final PostRepository    postRepository;

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
