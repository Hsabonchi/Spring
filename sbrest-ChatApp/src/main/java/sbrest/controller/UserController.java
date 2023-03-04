package sbrest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbrest.model.User;
import sbrest.model.dao.GroupDao;
import sbrest.model.dao.UserDao;



@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserDao userDao;
  @Autowired
  private GroupDao groupDao;

  @GetMapping
  public List<User> getUsers(ModelMap models) {
    return userDao.getUsers();
  }



  @GetMapping("/{id}")
  public User get(@PathVariable Integer id) {
    User userObj = userDao.getUser(id);
    // userObj.addGroups(groupDao.getGroup(0));
    userDao.saveUser(userObj);
    return userObj;
  }

  @PutMapping("/{id}")
  private void update(@PathVariable Integer id, @RequestBody User user) {
    System.out.println(user.getUsername());
    User userObj = userDao.getUser(id);
    userObj.setUsername(user.getUsername());
    userDao.saveUser(userObj);
  }

}
