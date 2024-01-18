package social.tweet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import social.tweet.entity.Member;
import social.tweet.repository.MemberRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/")

    public List<Member> getMember(){
        return memberRepository.findAll();
    }
}
