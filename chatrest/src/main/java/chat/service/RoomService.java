package chat.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import chat.dto.RoomDto;
import chat.entity.Member;
import chat.entity.Room;
import chat.repository.MemberRepository;
import chat.repository.RoomRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {

  private final RoomRepository roomRepository;
  // private final MediaFileRepository mediaFileRepository;
  private final MemberRepository memberRepository;

  public boolean addOrUpdate(RoomDto roomDto) {
    try {

      System.out.println("recieved roomDto --> " + roomDto);

      // room attributes
      Room room = new Room();
      room.setId(roomDto.getRoomId());
      room.setRoomName(roomDto.getRoomName());
      room.setTagName(roomDto.getTagName().toString());

      // Attachment attributes

      // Get MembersId and Save them
      Set<Long> setIds = roomDto.getMemberId();
      System.out.println("setIds == > " + setIds);

      if (setIds != null) {
        List<Member> listMembers = memberRepository.findAllById(setIds);
        System.out.println("listMembersObj == > " + listMembers);
        room.getMembers().addAll(listMembers);
        System.out.println("room.getMembers() == > " + room.getMembers());
      }

      roomRepository.save(room);

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public List<RoomDto> findAllRooms() {

    // holds all RoomDto objects as a final result
    List<RoomDto> roomDtoList = new ArrayList();
    // fetching all room from DB to convert room to roomDto
    for (Room room : roomRepository.findAll()) {

      System.out.println("room --> " + room);

      RoomDto roomDto = new RoomDto();
      roomDto.setRoomId(room.getId());
      roomDto.setRoomName(room.getRoomName());
      roomDto.setTagName(room.getTagName());
      // roomDtoList.add(roomDto);
      // empty set for Members
      Set<Long> setMem = new HashSet();

      System.out.println("room.getMembers() --> " + room.getMembers());
      Set<Member> setMembers = room.getMembers();
      System.out.println("setMembers --> " + setMembers);

      if (setMembers != null) {
        for (Member r : setMembers) {
          setMem.add(r.id);
        }
      }
      roomDto.setMemberId(setMem);
      roomDtoList.add(roomDto);
    }
    return roomDtoList;
  }

  public RoomDto findById(Long id) {

    Room roomObj = roomRepository.findById(id).get();
    RoomDto roomDto = new RoomDto();
    roomDto.setRoomId(roomObj.getId());
    roomDto.setRoomName(roomObj.getRoomName());
    roomDto.setTagName(roomObj.getTagName());

    Set<Long> setRoom = new HashSet();

    for (Member r : roomObj.getMembers()) {
      setRoom.add(r.getId());
    }

    roomDto.setMemberId(setRoom);

    return roomDto;



  }

}
