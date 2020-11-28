package cn.sea.controller;

import cn.sea.entity.User;
import cn.sea.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

@Api(tags = "关于用户操作的控制器UserController")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("用户登录，登录成功后将其保存到session域中")
    @PostMapping("/login")
    public String login(@ApiParam("用户实体") User user, @ApiIgnore HttpSession session) {
        User login = userService.login(user);
        if ( login != null ) {
            System.out.println("login = " + login);
            session.setAttribute("user", login);
            return "redirect:/file/showAll";
        } else {
            return "redirect:/index";
        }
    }

    @ApiOperation("测试方法")
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

}
