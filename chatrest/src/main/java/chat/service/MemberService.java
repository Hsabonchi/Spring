package chat.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import chat.dto.MemberDto;
import chat.entity.Member;
import chat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;



  public List<Member> findAllMembers() {

    return memberRepository.findAll();

  }

  public boolean addOrUpdate(MemberDto memberDto) {
    try {

      // System.out.println("memberDto --> " + memberDto);
      // Member attributes
      Member member = new Member();
      member.setId(memberDto.getMemberId());
      member.setUserName(memberDto.getUserName());
      // Encrypt the password then save it into the DB
      member.setPassword( new BCryptPasswordEncoder().encode(memberDto.getPassword()));
      member.setPrimaryEmail(memberDto.getPrimaryEmail());
      member.setAdmin(memberDto.isAdmin());
      member.setModerator(memberDto.isModerator());
      // System.out.println(member);
      memberRepository.save(member);

    } catch (Exception e) {
      System.out.println(e);
      return false;

    }
    return true;
  }

}
