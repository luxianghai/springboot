package cn.sea.controller;

import cn.sea.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 使用thymeleaf
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/findAll")
    public String findAll(HttpServletRequest request, Model model) {

        System.out.println("查询所有");
        model.addAttribute("name", "小陈");
        model.addAttribute("username","<a href=''>小车车</a>");
        model.addAttribute("user", new User("100", "xiaohei", 23, new Date()));

        List<User> users = Arrays.asList(new User("100", "xiaohei", 23, new Date()),
                new User("101", "小小黑", 10, new Date()),
                new User("102", "小车车", 20, new Date()));
        model.addAttribute("users", users);
        return "index"; // 逻辑名： classpath:/templates/逻辑名.html
    }

    @GetMapping("/delete")
    @ResponseBody  // 将返回值转为json格式
    public String delete(String id, String name) {

        System.out.println("id = " + id + ", name = " + name + " 删除");
        return "id: "+id + ", name: " +name + " 的用户被删除";
    }
}
