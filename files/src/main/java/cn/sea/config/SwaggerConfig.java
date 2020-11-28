package cn.sea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 // 开启Swagger
public class SwaggerConfig {

    public static final Contact CONTACT = new Contact("大海", "http://8.129.218.31:9991/lxh_files/index", "3243016771@qq.com");

    // 配置 Swagger 的 Docket 的 bean 实例
    @Bean
    public Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("大海")
                // enable: 是否启动swagger，如果为false则不能在浏览器中访问swagger
                .enable(true)
                .select()
                // RequestHandlerSelectors：扫描接口的方式
                // basePackage(package)：指定要扫描的包
                // any(); 扫描全部  none(); 不扫描
                // withClassAnnotation(RestController.class)：扫描类上的注解，参数是一个注解的字节码文件
                // withMethodAnnotation(GetMapping.class)：扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("cn.sea.controller"))
                // paths: 过滤路径
                //.paths(PathSelectors.ant("/lu/**")) // 只扫描指定路径

                .build(); // build
    }

    /*@Bean
    public Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) // Swagger 的信息
                .groupName("Luxianghai")
                .enable(true)
                .select()
                //.apis(RequestHandlerSelectors.basePackage("cn.sea.controller"))
                .build();
    }*/

    /*// 配置Swagger的信息
    private ApiInfo apiInfo() {
        return new ApiInfo("LXH的SpringBoot文件上传下载SwaggerAPI文档",
                "Talk is cheap, show me the code",
                "v1.0",
                "http://8.129.218.31:9991/lxh_files/index",
                CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }*/

    // 配置swagger的信息
    private ApiInfo apiInfo() {
        return new ApiInfo("柒拾贰的SwaggerAPI文档",
                "放弃不难，但坚持一定很酷",
                "v1.0",
                "http://8.129.218.31:88/image/",
                CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }

}
