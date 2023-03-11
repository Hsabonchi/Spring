package chatrest.model.dao;

import java.util.List;
import chatrest.model.Room;

public interface RoomDao {

  // create
  Room saveRoom(Room room);

  // read
  List<Room> getRoom();

  Room getRoom(Integer id);

  // update
  void updateRoom(Room room);

  // delete
  void delete(String roomName);

}
