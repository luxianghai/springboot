package cn.sea.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Aspect // 明确该类是一个切面类
@Configuration
@Order(2)
public class MyAspect {

    // 环绕通知
    //@Order(2) // 设置环绕通知的优先级，值越小优先级越高,在SpringBoot中加在方法上无效，需要加在类上
    @Around("within(cn.sea.service.impl.*ServiceImpl)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("进入环绕通知0 ~ ~");
        System.out.println("目标方法0："+ joinPoint.getSignature().getName());
        System.out.println("方法参数0：" + joinPoint.getArgs());
        System.out.println("目标对象0："+ joinPoint.getTarget());
        Object proceed = joinPoint.proceed();// 执行切入点方法，返回值为方法执行结束后的返回值
        System.out.println("目标方法执行之后的业务处理0 ~ ~ ");
        System.out.println("proceed0 = "+proceed);
        return proceed;
    }

    /*// 前置通知方法，在目标执行之前执行
    //@Before("within(cn.sea.service.impl.*ServiceImpl)")
    @Before("execution(* cn.sea.service.impl.*ServiceImpl.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("目标方法名："+joinPoint.getSignature().getName());
        System.out.println("目标方法参数：" + joinPoint.getArgs());
        System.out.println("目标对象：" + joinPoint.getTarget());
        System.out.println("前置通知业务处理 ~ ~");
    }

    // 后置通知
    @After("within(cn.sea.service.impl.*ServiceImpl)")
    public void after(JoinPoint joinPoint) {
        System.out.println("后置通知业务处理 ~ ~");
    }*/


}
