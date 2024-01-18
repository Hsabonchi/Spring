package social.networking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import social.networking.entity.Member;

import java.util.List;

@Repository
public interface MemberRepository  extends MongoRepository<Member, String> {

    @Query("{ 'UserName' : ?0 }")
    public Member findByUserName(String UserName);

    @Query("{ 'deleted' : false }")
    public List<Member> findByDeletedIsFalse(Boolean deleted);


}
