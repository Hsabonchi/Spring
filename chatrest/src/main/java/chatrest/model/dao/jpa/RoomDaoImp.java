package chatrest.model.dao.jpa;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import chatrest.model.Room;
import chatrest.model.dao.RoomDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class RoomDaoImp implements RoomDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public Room saveRoom(Room room) {

    return entityManager.merge(room);
  }

  @Override
  public List<Room> getRooms() {

    return entityManager.createQuery("from Room", Room.class).getResultList();
  }

  @Override
  public Room getRoom(Integer id) {

    return entityManager.find(Room.class, id);
  }

  @Override
  public void updateRoom(Room room) {
    // TODO Auto-generated method stub

  }

  @Override
  public void delete(String roomName) {
    // TODO Auto-generated method stub

  }

}
