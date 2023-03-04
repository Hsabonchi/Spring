package sbrest.model.dao;

import java.util.List;
import sbrest.model.User;



public interface UserDao {
  List<User> getUsers();

  User getUser(Integer id);

  User saveUser(User user);
}
