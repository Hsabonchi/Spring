package chatrest.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import chatrest.dto.RoomDto;
import chatrest.entity.Attachment;
import chatrest.entity.Member;
import chatrest.entity.Room;
import chatrest.repository.AttachmentRepository;
import chatrest.repository.MemberRepository;
import chatrest.repository.RoomRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {

  private final RoomRepository roomRepository;
  private final AttachmentRepository attachmentRepository;
  private final MemberRepository memberRepository;

  public boolean addOrUpdate(RoomDto roomDto) {
    try {
      // room attributes

      Room room = new Room();
      room.setId(roomDto.getRoomId());
      room.setRoomName(roomDto.getRoomName());
      room.setTagName(roomDto.getTagName());

      // Attachment attributes

      List<Attachment> result = attachmentRepository.findAllById(roomDto.getAttachtId());
      Set<Attachment> attachments = new HashSet(result);

      room.setAttachment(attachments);

      Member member = null;

      List<Member> result2 = memberRepository.findAllById(roomDto.getMemberId());
      Set<Member> members = new HashSet(result2);

      room.setMember(members);

      roomRepository.save(room);

      // roomRepository.save(room);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public List<Room> getAllRooms() {

    return roomRepository.findAll();

  }

  // public Room getRoomById(Long id) {
  // return roomRepository.;
  // }

  // public boolean deleteRoomById(Long id) {
  // if (roomRepository.existsById(id)) {
  // Room room = roomRepository.findById(id).get();
  // roomRepository.delete(room);
  // return true;
  // } else {
  // return false;
  // }
  // }



}
