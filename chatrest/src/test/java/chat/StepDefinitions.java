package chat;

import chat.entity.Post;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StepDefinitions {

    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String postUrl = "http://localhost";

    private String postId = "";

    @Given("I can create a new post")
    public void i_can_create_a_new_post() {
        String url = postUrl + ":" + port + "/post";
        List<Post> allPost = restTemplate.getForObject(url, List.class);
        //log.info(allPost);
        assertTrue(!allPost.isEmpty());
    }

    @Given("^I sending post to be created with post id (.*), title (.*) and content (.*)$")
    public void i_sending_post(int post_id, String title, String content) {
        String url = postUrl + ":" + port + "/post";
        Post newPost = new Post();
        newPost.setId(Long.valueOf(post_id));
        newPost.setContent(title);
        newPost.setContent(content);
        Post post = restTemplate.postForObject(url, newPost, Post.class);
        postId = String.valueOf(post.getId());
        //log.info(post);
        assertNotNull(post);
    }

    @Then("I should be able to see my newly created post")
    public void i_should_see_my_newly_created_post() {
        String url = postUrl + ":" + port + "/post/" + postId;
        Post myPost = restTemplate.getForObject(url, Post.class);
        //log.info(myPost);
        assertNotNull(myPost);
    }
}