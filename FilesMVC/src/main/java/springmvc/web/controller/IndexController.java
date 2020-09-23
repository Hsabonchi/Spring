package springmvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/home.html")
    public String index(ModelMap models) {
        models.put("user", "John");
        return "home";
    }
}
