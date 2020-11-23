package cn.sea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 组合注解：@EnableAutoConfiguration + @ComponentScan + ...
public class Application {

    // 主入口类
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
