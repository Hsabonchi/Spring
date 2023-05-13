package chatrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import chatrest.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
