package chatrest.repository;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import chatrest.entity.Room;


@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

  @Query(value = "select * from room where tag_name like :value", nativeQuery = true)
  Set<Room> getByTagName(String value);

}
