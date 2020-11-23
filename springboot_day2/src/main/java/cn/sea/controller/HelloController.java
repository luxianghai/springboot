package cn.sea.controller;

import cn.sea.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
// @Import(User.class)  // java config 二（不推荐）
public class HelloController {

    @Autowired
    private User user;

    @GetMapping("/hello")
    public String hello() {
        System.out.println("hello springboot !!!");
        System.out.println(user);
        return "hello springboot";
    }
}
