package social.networking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import social.networking.dto.PostDto;
import social.networking.entity.Post;
import social.networking.repository.MediaFileRepository;
import social.networking.repository.MemberRepository;
import social.networking.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

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

            if (post.getParentId() != null) {
                postDto.setParentId(post.getParentId() );
            }

            if (post.getAuthorId()!= null) {
                postDto.setAuthorId(post.getAuthorId());
            }

            if (post.getFileId() != null) {
                postDto.setFileId(post.getFileId() );
            }
            dtos.add(postDto);
        }
        return dtos;

    }

}
