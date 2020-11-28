package cn.sea.config;

import cn.sea.interceptors.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义拦截器的配置类
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /*registry.addInterceptor(new MyInterceptor())// 注册拦截器
                //.addPathPatterns("/hello/**")  // 添加拦截的请求路径(可变长字符串)
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns("/user/findAll"); // 指定哪些路径不拦截(可变长字符串)*/
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/findAll");
        /*registry.addInterceptor(new MyInterceptor2())
                .addPathPatterns("/**");*/
    }
}
