package cn.sea.controller;

import cn.sea.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Value("${name}")  // 将springboot配置文件中的 name 注入到该属性中
    private String name;
    // 注入springboot配置文件中的 day3.url 属性
    @Value("${day3.url}")
    private String url;
    @Value("${server.port}") // 注入springboot配置文件中的 server.port 属性
    private String port;
    // 注入日期数据
    @Value("${day3.date1}")
    private Date date1;
    @Value("${day3.date2}")
    private Date date2;
    // 注入数组
    @Value("${day3.names}")
    private String[] names;
    // 注入list集合
    @Value(("${day3.lists}"))
    private List<String> lists;
    // 注入 map 集合
    @Value("#{${day3.maps}}")
    private Map<String, String> maps;

    @Autowired // 注入对象
    private User user;

    @GetMapping("/hello")
    public String hello() {
        System.out.println("hello springboot !!!");
        System.out.println("name = " + name);
        System.out.println("url = " + url);
        System.out.println("port = " + port);
        System.out.println("date1 = " + date1);
        System.out.println("date2 = " + date2);
        for( String s: names ) {
            System.out.println("names = " + s);
        }
        lists.forEach(s -> System.out.println("lists = " + s));
        maps.forEach( (k,v) -> System.out.println("k = " + k + " ,v = " + v));

        System.out.println("=====================================");
        System.out.println("user = " + user);
        return "hello springboot !!!";
    }
}
