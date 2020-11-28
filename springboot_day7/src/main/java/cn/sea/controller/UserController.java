package cn.sea.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @GetMapping("/findAll")
    @ResponseBody
    public String findAll() {
        log.info("进入findAll方法. . .");
        return "findAll";
    }

    @GetMapping("/save")
    @ResponseBody
    public String save() {
        log.info("进入save方法. . .");
        return "save";
    }
}
