package at.fhv.itb17.s5.teamb.apis.rest.api.website;

import org.springframework.stereotype.Controller;

@Controller
public class ResourceControllerImpl implements ResourceController {
    @Override
    public String indexPage() {
        return "static/index.html";
    }
}
