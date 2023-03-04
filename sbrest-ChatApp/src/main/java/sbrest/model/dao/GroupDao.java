package sbrest.model.dao;

import java.util.List;
import sbrest.model.Group;



public interface GroupDao {

  List<Group> getGroup();

  Group saveGeroup(Group group);

  Group getGroup(Integer id);



}
