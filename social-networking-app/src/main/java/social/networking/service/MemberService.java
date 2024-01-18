package social.networking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import social.networking.dto.MemberDto;
import social.networking.entity.Member;
import social.networking.repository.MemberRepository;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

@Service

public class MemberService {
    @Autowired

    MemberRepository memberRepository;

    public List<MemberDto> findByDeletedIsFalse() {

        List<Member> members = memberRepository.findByDeletedIsFalse(fals);

        List<MemberDto> listDtos = new ArrayList();

        for (Member member : members) {
            MemberDto memberDto = new MemberDto();
            memberDto.setMemberId(member.getId());
            memberDto.setUserName(member.getUserName());
            memberDto.setPrimaryEmail(member.getPrimaryEmail());
            memberDto.setAdmin(member.isAdmin());
            memberDto.setModerator(member.isModerator());
            listDtos.add(memberDto);
        }
        return listDtos;
    }

    public boolean saveMember(MemberDto memberDto) throws Exception {
        try {
            if (memberDto == null || memberDto.getUserName().equals("") || memberDto.getPassword().equals("")) {
                throw new Exception ("Member is not valid either userName or password is null  or the passed memberDto is nul");

            } else {
                // Member attributes
                Member member = new Member();
                //member.setId(memberDto.getMemberId());
                member.setUserName(memberDto.getUserName());
                // Encrypt the password then save it into the DB
                member.setPassword(memberDto.getPassword());
                member.setPrimaryEmail(memberDto.getPrimaryEmail());
                member.setAdmin(memberDto.isAdmin());
                member.setModerator(memberDto.isModerator());
                memberRepository.save(member);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public MemberDto getByUserName(String UserName){

        Member member = memberRepository.findByUserName(UserName);
        if(member == null){
            return null;
        }
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId(member.getId());
        memberDto.setUserName(member.getUserName());
        memberDto.setPrimaryEmail(member.getPrimaryEmail());
        memberDto.setAdmin(member.isAdmin());
        memberDto.setModerator(member.isModerator());
        return memberDto;
    }


}

