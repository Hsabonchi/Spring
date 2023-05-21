package chatrest.conroller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import chatrest.dto.MemberDto;
import chatrest.entity.Member;
import chatrest.entity.Room;
import chatrest.service.MemberService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {


  private final MemberService memberService;

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
  private String addRoom(@RequestBody MemberDto memberDto) {
    memberService.addOrUpdate(memberDto);
    return "Success";
  }

}

