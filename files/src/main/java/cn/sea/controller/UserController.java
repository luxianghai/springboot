package cn.sea.controller;

import cn.sea.entity.User;
import cn.sea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(User user, HttpSession session) {
        User login = userService.login(user);
        if ( login != null ) {
            System.out.println("login = " + login);
            session.setAttribute("user", login);
            return "redirect:/file/showAll";
        } else {
            return "redirect:/index";
        }
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

}
