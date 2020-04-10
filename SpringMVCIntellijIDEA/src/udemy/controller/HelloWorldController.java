package udemy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tuna on 24/02/2017.
 */

@Controller
public class HelloWorldController {

    @RequestMapping("/")
    public String helloWorld() {
        return "index";
    }
}
