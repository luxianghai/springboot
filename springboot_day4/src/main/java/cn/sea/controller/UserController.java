package cn.sea.controller;

import cn.sea.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * springboot 集成 jsp
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/findAllB")
    public String findAllB(HttpServletRequest request, Model model) {
        System.out.println("查询所有1");
        model.addAttribute("name", "你好");
        model.addAttribute("users", Arrays.asList(new User("zhangsan", 13, new Date()), new User("lisi", 18, new Date())));
        return "forward:/index.jsp";
    }



    @GetMapping("/findAll")
    public String findAll() {
        System.out.println("查询所有3");
        return "index"; // 跳转页面的 逻辑名  前缀+逻辑名+后缀 = /index.jsp
    }
}
