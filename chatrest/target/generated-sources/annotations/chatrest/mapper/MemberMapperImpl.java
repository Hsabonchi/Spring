package chatrest.mapper;

import chatrest.dto.MemberDto;
import chatrest.entity.Member;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-07T07:51:33-0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberDto toMemberDto(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDto memberDto = new MemberDto();

        memberDto.setUserName( member.getUserName() );
        memberDto.setPassword( member.getPassword() );
        memberDto.setPrimaryEmail( member.getPrimaryEmail() );
        memberDto.setAdmin( member.isAdmin() );
        memberDto.setModerator( member.isModerator() );

        return memberDto;
    }
}
