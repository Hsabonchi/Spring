package chatrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import chatrest.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {



}
