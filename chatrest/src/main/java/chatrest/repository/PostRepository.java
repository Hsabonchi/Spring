package chatrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import chatrest.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {



}
