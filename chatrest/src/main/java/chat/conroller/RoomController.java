package chat.conroller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Room addUserToRoom(@PathVariable Long id, @RequestBody RoomDto RoomDto) {
        System.out.println("id ==>  " + id + "RoomDto  ==> " + RoomDto);
        return roomService.addUser(id, RoomDto);

    }

}

