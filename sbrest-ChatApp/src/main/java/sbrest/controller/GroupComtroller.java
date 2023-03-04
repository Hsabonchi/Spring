package sbrest.controller;



import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import sbrest.model.Group;
import sbrest.model.dao.GroupDao;

@RestController
@RequestMapping("/groups")
public class GroupComtroller {

  @Autowired
  private GroupDao groupDao;

  @GetMapping
  public List<Group> getTopLevel(ModelMap models) {
    return groupDao.getGroup();
  }

  @GetMapping("/{id}")
  public Group getbyId(@PathVariable Integer id) {
    Group g = groupDao.getGroup(id);
    if (g == null)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group Not Found");
    return g;
  }

  @PostMapping("/groups")
  public String add(Group group) {
    groupDao.saveGeroup(group);
    return "Created";

  }

  // 6) Upload a image to an existing group/room
  @PostMapping("upload/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public String uploadToFolder(@PathVariable Integer id, @RequestParam("file") MultipartFile file)
      throws IOException {

    Group groupObj = groupDao.getGroup(id);
    String fileName = file.getOriginalFilename();
    groupObj.setContent(file.getBytes());
    return "UPLOADED SUCCESFULLY " + "ID " + id + "  ---->" + fileName;


  }

}


