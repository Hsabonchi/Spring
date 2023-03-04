package sbrest.model.dao.jpa;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sbrest.model.User;
import sbrest.model.dao.UserDao;

@Repository
public class UserDaoImp implements UserDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<User> getUsers() {

    return entityManager.createQuery("from User", User.class).getResultList();
  }

  @Override
  public User getUser(Integer id) {
    User userobj = entityManager.find(User.class, id);
    return userobj;
  }

  @Override
  @Transactional
  public User saveUser(User user) {
    return entityManager.merge(user);

  }

}
