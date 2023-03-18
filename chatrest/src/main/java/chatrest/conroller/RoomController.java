package chatrest.conroller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import chatrest.model.Room;
import chatrest.model.dao.RoomDao;

@RestController
@RequestMapping("/rooms")
public class RoomController {

  Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  RoomDao roomDao;

  @GetMapping
  @ResponseStatus(HttpStatus.ACCEPTED)
  private List<Room> getAllRooms(ModelMap models) {
    return roomDao.getRooms();
  }


  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  private Room getRoom(@PathVariable(required = true) Integer id) {
    if (id != null) {
      return roomDao.getRoom(id);
    } else {
      return null;
    }
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  private String addRoom(@RequestBody Room room) {
    roomDao.saveRoom(room);
    return "Success";
  }

}

