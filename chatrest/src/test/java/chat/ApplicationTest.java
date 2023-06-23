package chat;

import chat.dto.PostDto;
import chat.service.PostService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ApplicationTest {
    @Autowired
     PostService  postservice;

    @Test
    public void contextLoads(){
        List<PostDto> allPost = postservice.getAllPost();
        System.out.println( allPost );
    }

}
