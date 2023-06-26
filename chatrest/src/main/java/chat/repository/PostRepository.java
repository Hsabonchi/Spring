package chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import chat.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    public List<Post> findByParent(Post post);

    @Modifying
    @Query(value = "delete from post where parent_id=:id",nativeQuery = true)
    void deleteByParentId(Long id);

    @Query(value = "select * from post where parent_id=:id", nativeQuery = true)
    List <Post> findChildPost(Long id);





}
