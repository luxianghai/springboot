package cn.sea.controller;

import cn.sea.entity.User;
import cn.sea.service.UserService;
import cn.sea.util.ValidateImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 登录方法
    @PostMapping("/login")
    public String login(String username, String password, HttpSession session) {
        if( username == null || password == null ||  username.equals("") || password.equals("")) {
            return "redirect:/index"; // 跳转回到登录
        }
        User login = userService.login(username, password);
        if ( login != null ) {
            session.setAttribute("user",login);
            return "redirect:/emp/findAll"; // 跳转查询所有
        }
        return "redirect:/index";
    }

    // 注册方法
    @PostMapping("/register")
    public String register(User user, String code, HttpSession session) {

        String sessionCode = (String) session.getAttribute("code");
        if ( sessionCode.equalsIgnoreCase(code)) {
            userService.save(user);
            return "redirect:/index"; // 跳转到登录界面
        }

        return "redirect:/toRegister";
    }

    // 生成验证码
    @GetMapping("/code")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {

        // 生成验证码
        ValidateImageCodeUtils validate = new ValidateImageCodeUtils();
        BufferedImage image = validate.getImage();
        String code = validate.getText();

        session.setAttribute("code", code);
        //System.out.println("code = " +code);

        // 响应图片
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }


}
