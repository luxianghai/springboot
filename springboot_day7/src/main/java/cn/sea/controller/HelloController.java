package cn.sea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        System.out.println("========== 2 test =========");
        return "upload";
    }

    @GetMapping("/work")
    @ResponseBody
    public String work() {
        return "hello work";
    }
}
