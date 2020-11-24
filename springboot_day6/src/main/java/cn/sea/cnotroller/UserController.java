package cn.sea.cnotroller;

import cn.sea.entity.User;
import cn.sea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    // findAll
    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "showAll";
    }

    // save
    @GetMapping("/save")
    public String save(User user) {
        userService.save(user);
        return "redirect:/user/findAll";
    }
}
