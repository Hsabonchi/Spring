package chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import chat.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {



}
