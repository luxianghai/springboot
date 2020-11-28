package cn.sea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class IndexController {

    @GetMapping("/index")
    public String toLogin() {
        return "login";
    }

}
