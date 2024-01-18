package social.tweet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import social.tweet.entity.Post;

@Repository
public interface PostRepository  extends MongoRepository <Post, String>{
}
