package cn.sea.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {

        System.out.println("hello springboot !!!");
        return "hello springboot !!!";
    }
}
