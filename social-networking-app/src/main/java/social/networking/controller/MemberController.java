package social.networking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.networking.dto.MemberDto;
import social.networking.entity.Member;
import social.networking.repository.MemberRepository;
import social.networking.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
     MemberService memberService;

    @GetMapping
    public List<MemberDto> getMember(){
        return memberService.findByDeletedIsFalse();
    }

    @GetMapping("/{name}")
    public ResponseEntity<MemberDto> getMemberByUserName(@PathVariable String name){
        if(name.isEmpty() || name == null ){
            System.out.println("emptyyyy");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            MemberDto memberDto = memberService.getByUserName(name);
            if (memberDto == null){
                return new ResponseEntity<>(
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(memberDto,
                    HttpStatus.FOUND);

        }
    }
    @PostMapping
    public boolean addMember (@RequestBody MemberDto memberDto) throws Exception {
        return memberService.saveMember(memberDto);
    }
}
