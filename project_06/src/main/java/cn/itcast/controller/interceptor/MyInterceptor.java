package cn.itcast.controller.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* 自定义拦截器*/
public class MyInterceptor implements HandlerInterceptor {
    @Override
    /*
    * 预处理，controller方法执行前
    * return true 放行，执行下一个拦截器，如果没有，执行controller中的方法
    * return false 不放行
    * */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("未拦截");
        return true;
    }
}
