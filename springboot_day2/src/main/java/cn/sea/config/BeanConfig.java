package cn.sea.config;

import cn.sea.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Calendar;

@Configuration
public class BeanConfig {

    @Bean // @Bean 将当前方法的返回值作为作为工厂中的一个对象进行管理 在工厂中的默认标识：类名首字母小写
    public User getUser() {
        return new User();
    }

    @Bean // @Bean(name = "aaa")
    @Scope("prototype") // prototype：多例的  singleton：单例的（默认）
    public Calendar getCalendar() {
        return Calendar.getInstance();
    }
}
