package chat.service;


import java.util.ArrayList;
import java.util.List;

import chat.entity.Member;
import chat.repository.MediaFileRepository;
import org.springframework.stereotype.Service;
import chat.dto.PostDto;
import chat.entity.Post;
import chat.repository.MemberRepository;
import chat.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final MediaFileRepository mediaFileRepository;

    public List<PostDto> getAllPost() {

        List<PostDto> dtos = new ArrayList();

        for (Post post : postRepository.findAll()) {

            PostDto postDto = new PostDto();

            postDto.setPostId(post.getId());
            postDto.setContent(post.getContent());
            postDto.setNoLikes(post.getNoLikes());

            if (post.getParent() != null) {
                postDto.setParentId(post.getParent().getId());
            }

            if (post.getAuthor() != null) {
                postDto.setAuthorId(post.getAuthor().getId());
            }

            if (post.getFile() != null) {
                postDto.setFileId(post.getFile().getId());
            }
            dtos.add(postDto);
        }
        return dtos;

    }


    public Post add(PostDto postDto) {

        Post post = new Post();
        post.setContent(postDto.getContent());

        if (postDto.getParentId() != null) {
            Long id = postDto.getParentId();
            Post parent = postRepository.findById(id).get();
            post.setParent(parent);
        }

        if (postDto.getAuthorId() != null) {
            post.setAuthor(memberRepository.findById(postDto.getAuthorId()).get());
        }

        if (postDto.getFileId() != null) {
            post.setFile(mediaFileRepository.findById(postDto.getFileId()).get());
        }
        post = postRepository.save(post);
        return post;
    }


    public PostDto getPostById(Long id) {

        Post postObj = postRepository.findById(id).get();

        PostDto postDto = new PostDto();

        postDto.setContent(postObj.getContent());
        postDto.setNoLikes(postObj.getNoLikes());
        postDto.setPostId(postObj.getId());

        if (postObj.getParent() != null) {
            postDto.setParentId(postObj.getParent().getId());
        }

        if (postObj.getAuthor() != null) {
            postDto.setAuthorId(postObj.getAuthor().getId());
        }
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