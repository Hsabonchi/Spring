package chatrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import chatrest.entity.Member;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  /*

     change it to findByUserName
     Optional <Member> findByUsername (String username)
   */
  @Query(value = "select * from member where user_name = :value", nativeQuery = true)
  Member getByName(String value);
}
