package cn.sea.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Aspect // 明确该类是一个切面类
@Configuration
@Order(1)
public class MyAspect2 {

    // 环绕通知
    @Order(1)
    @Around("within(cn.sea.service.impl.*ServiceImpl)")
    public Object around1(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("进入环绕通知1 ~ ~");
        System.out.println("目标方法1："+ joinPoint.getSignature().getName());
        System.out.println("方法参数1：" + joinPoint.getArgs());
        System.out.println("目标对象1："+ joinPoint.getTarget());
        Object proceed = joinPoint.proceed();// 执行切入点方法，返回值为方法执行结束后的返回值
        System.out.println("目标方法执行之后的业务处理1 ~ ~ ");
        System.out.println("proceed1 = "+proceed);
        return proceed;
    }

}
