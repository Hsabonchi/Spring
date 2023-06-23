package chat.mapper;
import chat.dto.PostDto;
import chat.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PostMapper {

//    @Mapping(target ="PostDto.authorId", source = "author")
    PostDto toPostDto (Post post);

}
