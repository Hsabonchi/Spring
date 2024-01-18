package social.networking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import social.networking.entity.Post;

@Repository
public interface PostRepository  extends MongoRepository <Post, String>{
}
