package chatrest.mapper;
import chatrest.dto.PostDto;
import chatrest.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PostMapper {

//    @Mapping(target ="PostDto.authorId", source = "author")
    PostDto toPostDto (Post post);

}
