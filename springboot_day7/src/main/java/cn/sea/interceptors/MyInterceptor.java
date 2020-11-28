package cn.sea.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 */
@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    // 预先处理 最先执行  返回值：true-放行 false-中断请求
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       log.info(" 001 preHandle");
        String username = request.getParameter("username");
        log.info("username = "+username);
        /*if( username == null || username.trim().equals("")  ) {
            response.sendRedirect(request.getContextPath() + "/upload");
            return false;
        }*/
        return true;
    }

    // controller执行完成后执行的回调函数
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info(" 001 postHandle");
    }

    // 响应完成后执行的回调函数
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info(" 001 afterCompletion");
    }
}
