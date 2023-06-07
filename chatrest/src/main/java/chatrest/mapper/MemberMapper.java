package chatrest.mapper;

import chatrest.dto.MemberDto;
import chatrest.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring",  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MemberMapper {

    MemberDto toMemberDto (Member member);
}
