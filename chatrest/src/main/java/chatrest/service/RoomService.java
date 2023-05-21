package chatrest.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import chatrest.dto.RoomDto;
import chatrest.entity.Member;
import chatrest.entity.Room;
import chatrest.repository.MemberRepository;
import chatrest.repository.RoomRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {

  private final RoomRepository roomRepository;
  // private final MediaFileRepository mediaFileRepository;
  private final MemberRepository memberRepository;

  public boolean addOrUpdate(RoomDto roomDto) {
    try {

      System.out.println("roomDto --> " + roomDto);
      // room attributes
      Room room = new Room();
      room.setId(roomDto.getRoomId());
      room.setRoomName(roomDto.getRoomName());
      room.setTagName(roomDto.getTagName().toString());

      // Attachment attributes

      // List<MediaFile> mediaFileList = mediaFileRepository.findAllById(roomDto.getAttachtId());
      // Set<MediaFile> attachments = new HashSet(mediaFileList);
      // room.setFiless(attachments);

      // Member attributes

      Set<Long> setIds = roomDto.getMemberId();
      System.out.println("setIds == > " + setIds);
      if (setIds != null) {
        List<Member> listMembers = memberRepository.findAllById(setIds);
        System.out.println("listMembers == > " + listMembers);
        room.getMembers().addAll(listMembers);
        System.out.println("room.getMembers() == > " + room.getMembers());
      }

      // System.out.println("members --> " + members);
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
      roomDtoList.add(roomDto);
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
    }
    return roomDtoList;
  }

  public Room findById(Long id) {

    return roomRepository.findById(id).get();

  }
}
