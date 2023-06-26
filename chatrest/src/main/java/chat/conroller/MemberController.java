package chat.conroller;

import java.util.List;

import chat.utils.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import chat.dto.MemberDto;
import chat.entity.Member;
import chat.entity.Room;
import chat.service.MemberService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {


  private final MemberService memberService;
  private final JwtTokenUtil jwtTokenUtil;

  @GetMapping
  @ResponseStatus(HttpStatus.ACCEPTED)
  private List<Member> findAllRooms(ModelMap models) {
    return memberService.findAllMembers();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  private Room getRoom(@PathVariable(required = true) Long id) {
    if (id != null) {
      return null;
    } else {
      return null;
    }

  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  private String addMember(@RequestBody MemberDto memberDto) {
    memberService.addOrUpdate(memberDto);
    return "Success";
  }



}

