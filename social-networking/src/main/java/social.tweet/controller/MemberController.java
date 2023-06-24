package demomongodb.controller;


import demomongodb.entity.Member;
import demomongodb.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class MemberController {

    private final MemberRepository  memberRepository;

    @GetMapping("/")

    public List<Member> getMember(){
        return memberRepository.findAll();
    }
}
