package social.tweet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import social.tweet.entity.Post;
import social.tweet.repository.PostRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")

public class PostController {

    @Autowired
    PostRepository postRepository;

    @PostMapping
    public boolean addPost(@RequestBody Post post){

        postRepository.save(post);
//        if ( post.getParentId() != null){
//            Post parentPost = postRepository.findById(post.getParentId()).get();
//            parentPost.getChild().add(post.getId());
//            postRepository.save(parentPost);
//        }
        return true;
    }


}
