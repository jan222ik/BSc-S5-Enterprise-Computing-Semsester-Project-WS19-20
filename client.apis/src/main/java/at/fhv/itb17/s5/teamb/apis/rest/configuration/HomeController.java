package at.fhv.itb17.s5.teamb.apis.rest.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home redirection to swagger api documentation
 */
@Controller
public class HomeController {
    @GetMapping(value = "/doc")
    public String index() {
        System.out.println("swagger-ui.html");
        return "redirect:swagger-ui.html";
    }
}
