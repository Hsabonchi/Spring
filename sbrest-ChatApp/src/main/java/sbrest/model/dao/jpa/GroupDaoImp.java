package sbrest.model.dao.jpa;

import java.util.List;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sbrest.model.Group;
import sbrest.model.dao.GroupDao;


@Repository
public class GroupDaoImp implements GroupDao {

  @PersistenceContext
  private EntityManager entityManager;


  @Override
  public List<Group> getGroup() {

    return entityManager.createQuery("from Group", Group.class).getResultList();
  }


  @Override
  public Group getGroup(Integer id) {

    return entityManager.find(Group.class, id);
  }

  @Override
  @Transactional
  public Group saveGeroup(Group group) {
    return entityManager.merge(group);
  }

}
