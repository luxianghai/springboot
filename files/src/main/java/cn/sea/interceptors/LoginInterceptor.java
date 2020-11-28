package cn.sea.interceptors;

import cn.sea.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1. 获取请求URL
        String uri = request.getRequestURI();
        System.out.println("uri = " + uri);

        // 2. 判断是否包含登录相关的资源路径
        if( uri.contains("/index") || uri.contains("/login")) {
            return true;
        } else { // 不包含， 需要验证用户是否登录
            // 从session中获取 用户的登录信息
            User user = (User) request.getSession().getAttribute("user");
            if ( user != null ) { // 说明用户已经登录，放行
                return true;
            } else {
                String contextPath = request.getContextPath(); // 获取项目路径   /files
                response.sendRedirect(contextPath + "/index"); // 重定向到登录页面
                return false;
            }
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle......");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterHandle......");
    }
}
