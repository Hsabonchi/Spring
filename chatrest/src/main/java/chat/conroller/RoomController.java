package chat.conroller;

import java.util.List;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import chat.dto.RoomDto;
import chat.entity.Room;
import chat.repository.RoomRepository;
import chat.service.RoomService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rooms")
public class RoomController {

  private final RoomRepository roomRepository;
  private final RoomService roomService;

  @GetMapping
  @ResponseStatus(HttpStatus.ACCEPTED)
  private List<RoomDto> findAllRooms(ModelMap models) {
    return roomService.findAllRooms();
  }

  // Get All Room and total number of members in it
  // @GetMapping("/totalmemb")
  // public Map<String, Integer> getRoomMembers() {
  // // is this a good function naming
  // return ((Object) roomService).findAllMemberInRoom();
  // }


  @GetMapping("/{value}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  private Set<Room> searchRoomByTagName(@PathVariable(required = true) String value) {
    return roomRepository.getByTagName("%" + value + "%");

  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  private RoomDto getRoom(@RequestParam Long id) {

    return roomService.findById(id);


  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  private String addRoom(@RequestBody RoomDto roomDto) {
    roomService.addOrUpdate(roomDto);
    return "Success";
  }

}

