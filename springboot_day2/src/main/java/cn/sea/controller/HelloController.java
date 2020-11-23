package cn.sea.controller;

import cn.sea.entity.User;
import cn.sea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
@RequestMapping("/hello")
// @Import(User.class)  // java config 二（不推荐）
public class HelloController {

    @Autowired
    private User user;

    @Autowired
    private UserService userService;

    @Autowired
    private Calendar calendar;
    @Autowired
    private Calendar calendar1;

    @GetMapping("/hello")
    public String hello() {
        System.out.println("hello springboot !!!");
        System.out.println(user);

        userService.save(new User("10000", "luxianghai"));

        System.out.println(calendar.getTime());
        System.out.println(calendar==calendar1);
        return "hello springboot";
    }
}
