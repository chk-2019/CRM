package com.ck.interceptor;

import com.ck.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IsLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        System.out.println("登录拦截器执行了");
        String url = request.getRequestURI();
        if (url.contains("login.do"))
            return true;
        if (url.contains("welcome.do"))
            return true;
        if (url.contains("getUserListAndActivity.do"))
            return true;
        if (url.contains("getUserList.do"))
            return true;
        if (url.contains("save.do"))
            return true;
        if (url.contains("pageList.do"))
            return true;
        if (url.contains("delete.do"))
            return true;
        if (user==null){
            request.getRequestDispatcher("/fail.jsp").forward(request,response);
            return false;
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
