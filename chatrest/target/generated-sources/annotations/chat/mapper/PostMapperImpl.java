package chat.mapper;

import chat.dto.PostDto;
import chat.entity.Post;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-07T07:51:33-0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostDto toPostDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDto postDto = new PostDto();

        postDto.setContent( post.getContent() );
        postDto.setNoLikes( post.getNoLikes() );

        return postDto;
    }
}
