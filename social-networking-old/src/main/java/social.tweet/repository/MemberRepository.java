package social.tweet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import social.tweet.entity.Member;

@Repository
public interface MemberRepository  extends MongoRepository<Member, String> {

}
