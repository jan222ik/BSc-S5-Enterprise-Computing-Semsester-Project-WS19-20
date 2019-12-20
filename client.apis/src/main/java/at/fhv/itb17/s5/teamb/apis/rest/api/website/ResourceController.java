package at.fhv.itb17.s5.teamb.apis.rest.api.website;

import org.springframework.web.bind.annotation.GetMapping;

public interface ResourceController {

    @GetMapping(value = "/site")
    String indexPage();
}
