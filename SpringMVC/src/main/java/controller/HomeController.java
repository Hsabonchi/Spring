package controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// to let spring this is a controller,we use @Controller
///home.html
@Controller
public class HomeController {
	
//@ResponseBody
@RequestMapping("/home.html")
    public String home()
    {
        return "home";
    }

}

